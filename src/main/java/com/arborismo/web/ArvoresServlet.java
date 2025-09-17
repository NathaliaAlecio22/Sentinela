package com.arborismo.web;

import com.arborismo.model.Arvore;
import com.arborismo.model.Manutencao;
import com.arborismo.service.ArvoreService;
import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.List;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
@WebServlet("/api/arvores/*")
public class ArvoresServlet extends HttpServlet {

    private ArvoreService arvoreService = new ArvoreService();
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String pathInfo = request.getPathInfo();
        if (pathInfo != null && pathInfo.length() > 1) {
            String[] splits = pathInfo.split("/");
            if (splits.length == 3 && "qrcode".equals(splits[1])) {
                // Rota para buscar árvore por QR Code: /api/arvores/qrcode/{codigoQr}
                handleGetByQrCode(request, response, out);
            } else if (splits.length == 2) {
                // Rota para listar árvores por projeto: /api/arvores/{projetoId}
                handleListByProject(request, response, out);
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print(gson.toJson(new ApiResponse("Rota GET inválida.")));
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print(gson.toJson(new ApiResponse("Rota GET inválida.")));
        }
    }

    private void handleGetByQrCode(HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws IOException {
        String pathInfo = request.getPathInfo();
        String codigoQr = pathInfo.substring(pathInfo.lastIndexOf('/') + 1);
        
        System.out.println("Backend recebeu o codigoQr: " + codigoQr);
        
        Arvore arvore = arvoreService.buscarArvorePorCodigo(codigoQr);
        if (arvore != null) {
            response.setStatus(HttpServletResponse.SC_OK);
            out.print(gson.toJson(arvore));
        } else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            out.print(gson.toJson(new ApiResponse("Árvore não encontrada.")));
        }
    }

    private void handleListByProject(HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws IOException {
        String pathInfo = request.getPathInfo();
        try {
            int projetoId = Integer.parseInt(pathInfo.substring(1));
            List<Arvore> arvores = arvoreService.listarArvoresPorProjeto(projetoId);
            response.setStatus(HttpServletResponse.SC_OK);
            out.print(gson.toJson(arvores));
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print(gson.toJson(new ApiResponse("ID do projeto inválido.")));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
        // Configura o Gson para lidar com o formato de data ISO 8601 (yyyy-MM-dd)
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, type, jsonDeserializationContext) -> LocalDate.parse(json.getAsJsonPrimitive().getAsString()))
            .create();
            
        String requestBody = request.getReader().lines().collect(java.util.stream.Collectors.joining());
        
        Arvore novaArvore = gson.fromJson(requestBody, Arvore.class);
        
        if (arvoreService.adicionarArvore(novaArvore)) {
            response.setStatus(HttpServletResponse.SC_CREATED);
            out.print(gson.toJson(new ApiResponse("Árvore adicionada com sucesso! Código QR: " + novaArvore.getCodigoQr())));
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print(gson.toJson(new ApiResponse("Falha ao adicionar a árvore.")));
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        
        // Configura o Gson para lidar com o formato de data ISO 8601 (yyyy-MM-dd)
        Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, type, jsonDeserializationContext) -> LocalDate.parse(json.getAsJsonPrimitive().getAsString()))
            .create();
        
        String requestBody = request.getReader().lines().collect(java.util.stream.Collectors.joining());
        
        Arvore arvoreAtualizada = gson.fromJson(requestBody, Arvore.class);

        if (arvoreAtualizada.getId() > 0 && arvoreService.atualizarArvore(arvoreAtualizada)) {
            response.setStatus(HttpServletResponse.SC_OK);
            out.print(gson.toJson(new ApiResponse("Árvore ID " + arvoreAtualizada.getId() + " atualizada com sucesso!")));
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print(gson.toJson(new ApiResponse("Falha ao atualizar a árvore.")));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String pathInfo = request.getPathInfo();
        if (pathInfo != null && pathInfo.length() > 1) {
            try {
                int arvoreId = Integer.parseInt(pathInfo.substring(1));
                if (arvoreService.removerArvore(arvoreId)) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    out.print(gson.toJson(new ApiResponse("Árvore ID " + arvoreId + " removida com sucesso!")));
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    out.print(gson.toJson(new ApiResponse("Árvore não encontrada ou falha ao remover.")));
                }
            } catch (NumberFormatException e) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                out.print(gson.toJson(new ApiResponse("ID da árvore inválido.")));
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print(gson.toJson(new ApiResponse("ID da árvore é obrigatório na URL.")));
        }
    }
    
    // Classes auxiliares para as respostas da API
    class ApiResponse {
        String message;
        public ApiResponse(String message) { this.message = message; }
    }
}