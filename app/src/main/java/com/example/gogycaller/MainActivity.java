package com.example.gogycaller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zegocloud.uikit.prebuilt.call.config.ZegoNotificationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationService;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editText=findViewById(R.id.editText);
        btn=findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startmyservice(editText.getText().toString());

                Intent intent = new Intent(getApplicationContext(), profile.class);
                intent.putExtra("caller", editText.getText().toString().trim());
                startActivity(intent);
            }
        });
    }

    public void startmyservice(String userid)
    {
        Application application = getApplication(); // Android's application context
        long appID = 1942823317;   // yourAppID
        String appSign ="f5d7b14c479fb32afd2609d62bf856cca7d23fdd3239b5b9cbe69305d1e40baf";  // yourAppSign
        String userID =userid; // yourUserID, userID should only contain numbers, English characters, and '_'.
        String userName =userid;   // yourUserName

        ZegoUIKitPrebuiltCallInvitationConfig callInvitationConfig = new ZegoUIKitPrebuiltCallInvitationConfig();
        callInvitationConfig.notifyWhenAppRunningInBackgroundOrQuit = true;
        ZegoNotificationConfig notificationConfig = new ZegoNotificationConfig();
        notificationConfig.sound = "zego_uikit_sound_call";
        notificationConfig.channelID = "CallInvitation";
        notificationConfig.channelName = "CallInvitation";
        ZegoUIKitPrebuiltCallInvitationService.init(getApplication(), appID, appSign, userID, userName,callInvitationConfig);
    }


    public void onDestroy(){
        super.onDestroy();
        ZegoUIKitPrebuiltCallInvitationService.unInit();
    }
}