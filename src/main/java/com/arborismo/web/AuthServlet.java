package com.arborismo.web;

import com.arborismo.model.Empresa;
import com.arborismo.service.EmpresaService;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

@WebServlet("/api/auth/*")
public class AuthServlet extends HttpServlet {

    private EmpresaService empresaService = new EmpresaService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();

        String pathInfo = request.getPathInfo();
        System.out.println("Recebendo requisição POST na rota: " + pathInfo);
        if (pathInfo == null || pathInfo.equals("/")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print(gson.toJson(new ApiResponse("Rota inválida.")));
            out.flush();
            return;
        }

        switch (pathInfo) {
            case "/login":
                handleLogin(request, response, out, gson);
                break;
            case "/cadastrar":
                handleCadastro(request, response, out, gson);
                break;
            default:
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                out.print(gson.toJson(new ApiResponse("Rota não encontrada.")));
                out.flush();
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response, PrintWriter out, Gson gson) throws IOException {
        String requestBody = request.getReader().lines().collect(java.util.stream.Collectors.joining());
        Empresa loginData = gson.fromJson(requestBody, Empresa.class);

        Empresa empresa = empresaService.login(loginData.getEmailPrincipal(), loginData.getSenha());
        if (empresa != null) {
            response.setStatus(HttpServletResponse.SC_OK);
            out.print(gson.toJson(new LoginResponse(true, "Login bem-sucedido.", "token-simulado-123", empresa.getId())));
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.print(gson.toJson(new ApiResponse("Email ou senha inválidos.")));
        }
    }

    private void handleCadastro(HttpServletRequest request, HttpServletResponse response, PrintWriter out, Gson gson) throws IOException {
        String requestBody = request.getReader().lines().collect(java.util.stream.Collectors.joining());
        Empresa novaEmpresa = gson.fromJson(requestBody, Empresa.class);
        novaEmpresa.setSenha(novaEmpresa.getSenha()); // Lógica de hash de senha iria aqui

        if (empresaService.cadastrarEmpresa(novaEmpresa)) {
            response.setStatus(HttpServletResponse.SC_CREATED);
            out.print(gson.toJson(new ApiResponse("Empresa cadastrada com sucesso!")));
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print(gson.toJson(new ApiResponse("Falha no cadastro.")));
        }
    }
    
    // Classes auxiliares para as respostas da API
    class ApiResponse {
        String message;
        public ApiResponse(String message) { this.message = message; }
    }
    class LoginResponse extends ApiResponse {
        boolean success;
        String token;
        int empresaId;
        public LoginResponse(boolean success, String message, String token, int empresaId) {
            super(message);
            this.success = success;
            this.token = token;
            this.empresaId = empresaId;
        }
    }
}