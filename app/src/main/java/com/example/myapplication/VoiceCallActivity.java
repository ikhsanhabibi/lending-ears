package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.example.myapplication.Adapters.AllUsersAdapter;
import com.example.myapplication.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sinch.android.rtc.PushPair;
import com.sinch.android.rtc.Sinch;
import com.sinch.android.rtc.SinchClient;
import com.sinch.android.rtc.calling.Call;
import com.sinch.android.rtc.calling.CallClient;
import com.sinch.android.rtc.calling.CallClientListener;
import com.sinch.android.rtc.calling.CallListener;
import com.sinch.android.rtc.calling.CallState;

import java.util.ArrayList;
import java.util.List;


public class VoiceCallActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    SinchClient sinchClient;
    Call call;
    ArrayList<User> userArrayList;
    DatabaseReference reference;

    ImageView layer_2, layer_3, hangupBtn;

    Animation layer2, layer3;

    TextView header;

    private Chronometer chronometer2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle parameters = getIntent().getExtras();

        if(parameters != null && parameters.containsKey("findlistener")){
            setContentView(parameters.getInt("findlistener"));
        layer_2 = (ImageView) findViewById(R.id.layer_2);
        layer_3 = (ImageView) findViewById(R.id.layer_3);


        layer2 = AnimationUtils.loadAnimation(this, R.anim.layer2);
        layer_2.startAnimation(layer2);

        layer3 = AnimationUtils.loadAnimation(this, R.anim.layer3);
        layer_3.startAnimation(layer3);
        }
        else
            setContentView(R.layout.activity_voice_call);



        // Change font
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Montserrat-Regular.ttf");

        header = (TextView) findViewById(R.id.header);
        header.setTypeface(typeface);


        // recyclerView
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userArrayList = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference().child("Stories");

        // Firebase
        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();

        //Sinch
        sinchClient = Sinch.getSinchClientBuilder()
                .context(this)
                .userId(firebaseUser.getUid())
                .applicationKey("9d009127-c52f-4e72-b839-76996ee0bdb2")
                .applicationSecret("GSx6G67kDkyOPOdS+pAVhw==")
                .environmentHost("clientapi.sinch.com")
                .build();

        sinchClient.setSupportCalling((true));
        sinchClient.startListeningOnActiveConnection();

        sinchClient.getCallClient().addCallClientListener(new SinchCallClientListener(){

        });

        sinchClient.start();
        fetchAllUsers();


    }

    private void fetchAllUsers() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userArrayList.clear();

                for (DataSnapshot dss:dataSnapshot.getChildren()) {
                    User user = dss.getValue(User.class);

                    userArrayList.add(user);
                }

                AllUsersAdapter adapter = new AllUsersAdapter((VoiceCallActivity.this),userArrayList);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"error: " + databaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private class SinchCallListener implements CallListener
    {


        @Override
        public void onCallProgressing(Call call) {
            Toast.makeText(getApplicationContext(), "Ringing...", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCallEstablished(final Call call) {
            Toast.makeText(getApplicationContext(), "Call established", Toast.LENGTH_SHORT).show();
            AlertDialog alertDialogCall = new AlertDialog.Builder(VoiceCallActivity.this).create();
            alertDialogCall.setTitle("CONNECTING");
            alertDialogCall.setMessage("Connecting..Just wait a little bit..");
            alertDialogCall.dismiss();
            setContentView(R.layout.activity_calling_screen);
            chronometer2 = findViewById(R.id.chronometer2);
            chronometer2.start();
            hangupBtn = findViewById(R.id.hangupBtn);
            hangupBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    call.hangup();
                }
            });
        }

        @Override
        public void onCallEnded(Call endedCall) {
            Toast.makeText(getApplicationContext(), "Call ended", Toast.LENGTH_SHORT).show();
            call = null;
            endedCall.hangup();
        }

        @Override
        public void onShouldSendPushNotification(Call call, List<PushPair> list) {

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_logout){
            if (firebaseUser!= null)
            {
                auth.signOut();
                finish();
                Intent i = new Intent(VoiceCallActivity.this,LoginActivity.class);
                startActivity(i);
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private class SinchCallClientListener implements CallClientListener
    {
        @Override
        public void onIncomingCall(CallClient callClient, final Call incomingcall) {
            // open dialog for incoming call

            AlertDialog alertDialog = new AlertDialog.Builder(VoiceCallActivity.this).create();
            alertDialog.setTitle("CALLING");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Reject", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    incomingcall.hangup();
                }
            });

            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Accept", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    call = incomingcall;
                    call.answer();
                    call.addCallListener(new SinchCallListener());
                    Toast.makeText(getApplicationContext(), "Call is started", Toast.LENGTH_LONG).show();
                }
            });

            alertDialog.show();
        }
    }


    public void callUser(User user)
    {
        if(call == null){
            call = sinchClient.getCallClient().callUser(user.getUserid());
            call.addCallListener(new SinchCallListener());
        }
    }

    /*private void openCallerDialog(final Call call) {
        AlertDialog alertDialogCall = new AlertDialog.Builder(VoiceCallActivity.this).create();
        alertDialogCall.setTitle("CONNECTING");
        alertDialogCall.setMessage("Connecting..Just wait a little bit..");
        alertDialogCall.setButton(AlertDialog.BUTTON_NEUTRAL, "Hang up", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                call.hangup();
            }
        });

        alertDialogCall.show();
    }*/
}
