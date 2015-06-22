/*
 * Copyright 2015 dsilva.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.apuntesdejava.aulavirtual.servlet;

import com.apuntesdejava.aulavirtual.ejb.AlumnoFacade;
import com.apuntesdejava.aulavirtual.entities.Alumno;
import java.io.DataInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author dsilva
 */
@MultipartConfig
@WebServlet(name = "RegistroAlumnoServlet", urlPatterns = {"/RegistroAlumnoServlet"})
public class RegistroAlumnoServlet extends HttpServlet {

    @EJB
    private AlumnoFacade alumnoFacade;
    private static final Logger LOG = Logger.getLogger(RegistroAlumnoServlet.class.getName());
    public static final DateFormat DATE_FORMAT_DD_MM_YYYY = new SimpleDateFormat("dd/MM/yyyy");

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String $id = request.getParameter("id");
            String nombre = request.getParameter("nombre");
            String sexo = request.getParameter("sexo");
            String email = request.getParameter("email");
            String $fechaNacimiento = request.getParameter("fechaNacimiento");
            Date fechaNacimiento = DATE_FORMAT_DD_MM_YYYY.parse($fechaNacimiento);
            Part fotoPart = request.getPart("foto");
            int fotoSize=(int)fotoPart.getSize(); //si no tiene tamaño, no hay foto
            
            byte[] foto=null; //el buffer
            if(fotoSize>0){
                foto=new byte[fotoSize];
                try(DataInputStream dis=new DataInputStream(fotoPart.getInputStream())){
                    dis.readFully(foto);
                    
                }
            }
            
            LOG.log(Level.INFO, "Nombre:{0}", nombre);
            LOG.log(Level.INFO, "sexo:{0}", sexo);
            LOG.log(Level.INFO, "email:{0}", email);
            LOG.log(Level.INFO, "fechaNacimiento:{0}", fechaNacimiento);

            boolean esNuevo = ($id == null || $id.isEmpty()); //si no tiene datos, es nuevo
            //si es nuevo, es new, sino, lo busca en la base de datos
            Alumno alumno = esNuevo ? new Alumno() : alumnoFacade.find(Long.valueOf($id));
            //guarda los valores como debe ser
            alumno.setNombre(nombre);
            alumno.setSexo(sexo);
            alumno.setEmail(email);
            alumno.setFechaNacimiento(fechaNacimiento);
            
            if (fotoSize>0)
                alumno.setFoto(foto);
            
            if (esNuevo) { //si es nuevo...
                alumnoFacade.create(alumno); //... lo crea
            } else {//... sino...
                alumnoFacade.edit(alumno); //.. lo actualiza
            }

            LOG.log(Level.INFO, "Alumno creado:{0}", alumno);
            RequestDispatcher rd = request.getRequestDispatcher("/alumno_reg_success.jsp");
            request.setAttribute("alumno", alumno);
            rd.forward(request, response);
        } catch (ParseException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
