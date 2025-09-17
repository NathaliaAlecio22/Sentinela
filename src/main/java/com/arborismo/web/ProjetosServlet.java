package com.arborismo.web;

import com.arborismo.model.Projeto;
import com.arborismo.service.ProjetoService;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/api/projetos/*")
public class ProjetosServlet extends HttpServlet {

    private ProjetoService projetoService = new ProjetoService();
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
        String empresaIdParam = request.getParameter("empresaId");
        
        if (empresaIdParam == null || empresaIdParam.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"message\":\"ID da empresa não fornecido.\"}");
            return;
        }

        try {
            int id_empresa = Integer.parseInt(empresaIdParam);
            List<Projeto> projetos = projetoService.listarProjetos(id_empresa);
            String json = gson.toJson(projetos);
            out.print(json);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print("{\"message\":\"ID da empresa inválido.\"}");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
            out.print("{\"message\":\"Erro interno no servidor.\"}");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String requestBody = request.getReader().lines().collect(java.util.stream.Collectors.joining());
        Projeto novoProjeto = gson.fromJson(requestBody, Projeto.class);

        // Lógica de autenticação e autorização (simplificada)
        // Assumimos que a empresa logada tem o ID 1
        novoProjeto.setEmpresaId(1);

        if (projetoService.adicionarProjeto(novoProjeto)) {
            response.setStatus(HttpServletResponse.SC_CREATED);
            out.print(gson.toJson(new ApiResponse("Projeto criado com sucesso!")));
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print(gson.toJson(new ApiResponse("Falha ao criar o projeto.")));
        }
        out.flush();
    }
    
    // Classe auxiliar para padronizar as respostas
    class ApiResponse {
        String message;
        public ApiResponse(String message) { this.message = message; }
    }
}