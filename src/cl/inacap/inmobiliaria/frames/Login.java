/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.inacap.inmobiliaria.frames;

import cl.inacap.inmobiliaria.dao.*;
import cl.inacap.inmobiliaria.dto.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import javax.swing.UIManager;
//import cl.inacap.inmobiliaria.tools.Hasher;

//import java.time.format.DateTimeFormatter;  
//import java.time.LocalDateTime; 
/**
 *
 * @author Ita
 */
public class Login extends javax.swing.JFrame {

    private static Cuenta cuenta;
    private static Empleado empleado;
    private static CuentaDAO cuentas = new CuentaDAO();

    /*
    private static ClienteDAO clientes = new ClienteDAO();
    private static EmpleadoDAO empleados = new EmpleadoDAO();
    private static PropiedadDAO propiedades = new PropiedadDAO();
    private static VentaDAO ventas = new VentaDAO();
    */
    
    public Login() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        usernameField = new javax.swing.JTextField();
        usernameLbl = new javax.swing.JLabel();
        tituloLbl_1 = new javax.swing.JLabel();
        tituloLbl_2 = new javax.swing.JLabel();
        passwordLbl = new javax.swing.JLabel();
        loginBtn = new javax.swing.JButton();
        passwordField = new javax.swing.JPasswordField();
        messageLbl = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 300, 220));
        setMinimumSize(new java.awt.Dimension(300, 220));
        setResizable(false);

        usernameField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        usernameField.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                usernameFieldActionPerformed(evt);
            }
        });

        usernameLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        usernameLbl.setText("Username");

        tituloLbl_1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tituloLbl_1.setText("SISTEMA DE ADMINISTRACION");

        tituloLbl_2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tituloLbl_2.setText("GESTION INMOBILIARIA");

        passwordLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        passwordLbl.setText("Password");

        loginBtn.setText("Ingresar");
        loginBtn.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                loginBtnActionPerformed(evt);
            }
        });

        passwordField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        passwordField.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                passwordFieldActionPerformed(evt);
            }
        });

        messageLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        messageLbl.setText("                          ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tituloLbl_1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tituloLbl_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(usernameLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(passwordLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 64, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(loginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 65, Short.MAX_VALUE))
                    .addComponent(messageLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tituloLbl_1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tituloLbl_2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(loginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(messageLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void usernameFieldActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_usernameFieldActionPerformed
    {//GEN-HEADEREND:event_usernameFieldActionPerformed
        logon();
    }//GEN-LAST:event_usernameFieldActionPerformed

    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_loginBtnActionPerformed
    {//GEN-HEADEREND:event_loginBtnActionPerformed
        logon();
    }//GEN-LAST:event_loginBtnActionPerformed

    private void passwordFieldActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_passwordFieldActionPerformed
    {//GEN-HEADEREND:event_passwordFieldActionPerformed
        logon();
    }//GEN-LAST:event_passwordFieldActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
         
        try{
            UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium." + "AluminiumLookAndFeel");
            //UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin." + "McWinLookAndFeel");
            //UIManager.setLookAndFeel("com.jtattoo.plaf.hifi." + "HiFiLookAndFeel");
        }catch(Exception ex){
            System.out.println("Te equivocaste de LookAndFeel" + ex.toString());
        }
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
             }
        });
    }
    
    /**
     * Proceso de autentificacion
     */
    private void logon()
    {
        //reset message label
        messageLbl.setText("");
        
        //blanks
        if (usernameField.getText().equals("") || passwordField.getText().equals(""))
        {
            messageLbl.setText("INGRESE CREDENCIALES");
            return;
        }
        
        int rol = cuentas.checkCredentials(usernameField.getText(), passwordField.getText());
        
        /*       
        LLAMAR A checkCredentials() USARA LOS METODOS GET Y SET DEL CAMPO usuario
        DE ESTA CLASE (Login) PARA PODER ALMACENAR DICHOS DATOS AQUI
        
        LO HARA SOLO CUANDO SE DETECTE QUE LA CUENTA ES VALIDA
        */
        
        //segun rol
        if (rol == 0)
        {            
            messageLbl.setText("CREDENCIALES INVALIDAS");
            return;
        }
        
        if (rol == -1)
        {
            messageLbl.setText("CUENTA DESHABILITADA");
            return;
        }
        
        //ventana vendedor
        if (rol == 1)
        {
            Vendedor vendedor = new Vendedor(cuenta, empleado);
            vendedor.setVisible(true);
            this.setVisible(false);
        }
        
        if (rol == 2)
        {
            Gerente gerente = new Gerente(cuenta, empleado);
            gerente.setVisible(true);
            this.setVisible(false);
        }
        
        if (rol == 3)
        {
            Administrador administrador = new Administrador(cuenta, empleado);
            administrador.setVisible(true);
            this.setVisible(false);
        }
        
        if (rol == 99)
        {
            messageLbl.setText("ERROR AL CONECTARSE A LA BASE DE DATOS");
            return; 
        }
        
    }
    
    public static void setCuenta (Cuenta acc)
    {
        cuenta = acc;
    }
    
    public static void setEmpleado (Empleado emp)
    {
        empleado = emp;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton loginBtn;
    private javax.swing.JLabel messageLbl;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLbl;
    private javax.swing.JLabel tituloLbl_1;
    private javax.swing.JLabel tituloLbl_2;
    private javax.swing.JTextField usernameField;
    private javax.swing.JLabel usernameLbl;
    // End of variables declaration//GEN-END:variables
}
