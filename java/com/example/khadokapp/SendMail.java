package com.example.khadokapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail extends AppCompatActivity {

    public String sEmail,sPassword,tosend,subject,Messages_khadok;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_mail);


        //tosend = Login.User_name;
        tosend = "190104056@aust.edu";

        subject  = "Your Order From Khadol";

        Messages_khadok = ShowCart.MyMessage;

        sEmail = "encryptedhacker777@gmail.com";
        sPassword = "assassins138fantasyalyx@remake";

        sendMail();

    }

    public void sendMail()
    {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp,port","587");


        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sEmail,sPassword);
            }
        });


        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sEmail));;


            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(tosend));

            message.setSubject(subject);

            message.setText(Messages_khadok);

            new SendMailTO().execute(message);

        }catch(MessagingException e)
        {
            e.printStackTrace();
        }



    }


    private class SendMailTO extends AsyncTask<Message,String,String> {
        /**
         * @param messages
         * @deprecated
         */
        @Override
        protected String doInBackground(Message... messages) {
            try {
                Transport.send(messages[0]);

                //Toast.makeText(SendMail.this,"Success",Toast.LENGTH_SHORT).show();
                return "Success";
            }catch (MessagingException e)
            {

                e.printStackTrace();
                //Toast.makeText(SendMail.this,"Error",Toast.LENGTH_SHORT).show();
                return "Error";
            }

        }

        /**
         * @param messages
         * @deprecated
         */

     /*   private ProgressBar progressDialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog  = ProgressDialog.show(SendMail.this,"Please Wait","Sending mail...!",true,false);


        }

        @Override
        protected String doInBackground(Message... messages) {
           try {
               Transport.send(messages[0]);
               return "Success";
           }catch (MessagingException e)
           {

               e.printStackTrace();
               return "Error";
           }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            if(s.equals("Success"))
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(SendMail.this);
                builder.setCancelable(false);
                builder.setTitle(Html.fromHtml("font color='#509324'>Success</font>"));
                builder.setMessage("Mail Sent Successfully");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                    }
                });
                builder.show();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Something wrong!!",Toast.LENGTH_SHORT).show();
            }

        }*/
    }
}