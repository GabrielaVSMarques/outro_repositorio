package br.noobies.cliente.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gabriela.vsmarques
 */
@WebServlet(name = "ClienteServlet", urlPatterns = {"/cliente"})
public class ClienteServlet extends HttpServlet {

        protected void processRequest(String metodoHttp, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
            // RECUPERA INFORMACOES DA REQUISICAO
            String nome = request.getParameter("nome");
            String cpf = request.getParameter("cpf");
            String endereco = request.getParameter("endereco");
            String telefone = request.getParameter("telefone");
            String email = request.getParameter("email");
            
             // VALIDAR DADOS
        boolean temErros = false;
        
        if (nome == null || nome.length() == 0) {
            temErros = true;
            request.setAttribute("erroNome", "Nome não preenchido");
        }
        if ( cpf == null || cpf.length() == 0) {
            temErros = true;
            request.setAttribute("erroCPF", "CPF não preenchido");
        }
        if (endereco == null || endereco.length() == 0) {
            temErros = true;
            request.setAttribute("erroEndereco", "Endereco não preenchido");
        }
        if (telefone == null || telefone.length() == 0) {
            temErros = true;
            request.setAttribute("erroTelefone", "Telefone não preenchido");
        }
        if (email == null || email.length() == 0) {
            temErros = true;
            request.setAttribute("erroEmail", "E-mail não preenchido");
        }

        if (temErros) {
            // REAPRESENTA FORMULARIO INDICANDO OS ERROS
            RequestDispatcher dispatcher = request.getRequestDispatcher("formularioCliente.jsp");
            dispatcher.forward(request, response);
        }

        // ARMAZENANDO VALORES COMO ATRIBUTOS
        request.setAttribute("metodoHttp", metodoHttp);
        request.setAttribute("nome", nome);
        request.setAttribute("cpf", cpf);
        request.setAttribute("endereco", endereco);
        request.setAttribute("telefone", telefone);
        request.setAttribute("email", email);
        
        RequestDispatcher dispatcher
                = request.getRequestDispatcher("/WEB-INF/jsp/resultadoCliente.jsp");
        dispatcher.forward(request, response); 
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest("GET",request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest("POST",request, response);
    }


}
