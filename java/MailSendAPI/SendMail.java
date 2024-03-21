package MailSendAPI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.khadokapp.Login;
import com.example.khadokapp.PlaceOrder;
import com.example.khadokapp.R;
import com.example.khadokapp.ShowCart;

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


    String tosend,subject,Message_tosend;


    String sEmail,sPassword;
    EditText to_send_mail,subject_tosend,message_to_send;
    Button send_mail_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_mail2);


        Message_tosend = ShowCart.MyMessage;
        tosend = Login.User_name;
        subject = "Your Order";


        sEmail = "encryptedhacker777@gmail.com";
        sPassword = "assassins138fantasyalyx@remake";

        send_mail_btn = findViewById(R.id.orderbtn_sendmail);

        send_mail_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Properties properties = new Properties();
                properties.put("mail.smtp.auth","true");
                properties.put("mail.smtp.starttls.enable","true");
                properties.put("mail.smtp.host","smtp.gmail.com");
                properties.put("mail.smtp.port","587");


                Session session = Session.getInstance(properties, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(sEmail,sPassword);
                    }
                });


                try{
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(sEmail));
                    message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse(tosend)
                            );


                    message.setSubject(subject);


                    new SendMail1().execute(message);

                    message.setText(Message_tosend);
                }catch (MessagingException e)
                {
                    e.printStackTrace();
                }
            }
        });

    }

    private class SendMail1 extends AsyncTask<Message,String,String> {

        /**
         * @param messages
         * @deprecated
         */

        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(SendMail.this,"please wait","Sending Mail....",true);
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
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);

            progressDialog.dismiss();
            if(s.equals("Success"))
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(SendMail.this);
                builder.setCancelable(false);
                builder.setTitle(Html.fromHtml("<font color='#509324'>Success</font>"));
                builder.setMessage("Mail send successfully");
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
                Toast.makeText(SendMail.this,"Failed to send!",Toast.LENGTH_SHORT).show();
            }
        }

    }
}