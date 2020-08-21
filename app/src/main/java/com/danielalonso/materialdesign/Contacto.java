package com.danielalonso.materialdesign;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Contacto extends AppCompatActivity {

    private MaterialToolbar toolbar;
    private TextInputEditText nombre;
    private TextInputEditText correo;
    private TextInputEditText mensaje;
    private MaterialButton btnEnviar;
    private String sEmail;
    private String sPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        nombre = findViewById(R.id.nombre);
        correo = findViewById(R.id.correo);
        mensaje = findViewById(R.id.mensaje);
        btnEnviar = findViewById(R.id.btnEnviar);

        //Remplazar por correo y contraseña
        sEmail = "usuario@gmail.com";
        sPassword = "Contraseña";

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Properties properties = new Properties();

                properties.put("mail.smtp.host", "smtp.gmail.com");
                properties.put("mail.smtp.socketFactory.port", "465");
                properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                properties.put("mail.smtp.port", "465");
                properties.put("mail.smtp.auth", "true");

                //Initialize session
                Session session = Session.getDefaultInstance(properties, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(sEmail, sPassword);
                    }
                });


                try {
                    //Initialize email content
                    Message message = new MimeMessage(session);
                    //Sender email
                    message.setFrom(new InternetAddress(sEmail));
                    //Recipient email
                    //Se puede remplazar sEmail para contestarle al usuario que nos pondremos en contacto proximamente
                    //En este caso se envía un correo al mismo correo con el que se inicia sesión
                    message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(sEmail));
                    //Email subject o Nombre del usuario
                    message.setSubject(nombre.getText().toString().trim());
                    //Email message
                    message.setText("From: " + correo.getText().toString().trim() + "\n\nBody: " + mensaje.getText().toString().trim());

                    //Send email
                    new SendEmail().execute(message);

                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        });

        //Agregando toolbar a la pantalla principal
        toolbar = findViewById(R.id.toolbar_contact);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private class SendEmail extends AsyncTask<Message, String, String> {
        //Initialize progress dialog
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(Contacto.this,
                    "Espere por favor", "Enviando correo...", true, false);
        }

        @Override
        protected String doInBackground(Message... messages) {
            try {
                Transport.send(messages[0]);
                return "Envío exitoso";
            } catch (MessagingException e) {
                e.printStackTrace();
                return "Error";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //Dismiss progress dialog
            progressDialog.dismiss();
            if (s.equals("Envío exitoso")) {
                //When success

                //Initialize alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(Contacto.this);
                builder.setCancelable(false);
                builder.setTitle(Html.fromHtml("<font color='#509324'>Envío exitoso</font>"));
                builder.setMessage("El correo se ha enviado con éxito");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        correo.setText("");
                        nombre.setText("");
                        mensaje.setText("");
                    }
                });

                //Show alert dialog
                builder.show();
            } else {
                //When error
                Toast.makeText(getApplicationContext(), "Algo salió mal, intente más tarde", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_favoritas, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            case R.id.aboutMe:
                // User chose the "Settings" item, show the app settings UI...
                Intent about = new Intent(this, AcercaDe.class);
                startActivity(about);
                break;

            case R.id.favorito:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                Intent fav = new Intent(this, Favoritas.class);
                startActivity(fav);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}