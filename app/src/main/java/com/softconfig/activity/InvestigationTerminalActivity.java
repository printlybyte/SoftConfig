package com.softconfig.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.softconfig.R;
import com.softconfig.Utils.ProperUtil;

import static com.softconfig.Utils.ProperUtil.getConfigProperties;
import static com.softconfig.Utils.ProperUtil.writeDateToLocalFile;
import static com.softconfig.Utils.PublicUtils.isIPAddress;

public class InvestigationTerminalActivity extends Activity implements View.OnClickListener {


    private EditText mIPEt;
    private EditText mPortNoEt;
    private EditText mUploadIntervalTimeEt;
    private EditText sBao_timer;
    /**
     * 提交
     */
    private Button mCommitIPBt;
    private EditText mDbCeilingEt;
    private EditText mClosePlayModeTimeEt;
    /**
     * 提交
     */
    private Button mCommitFlyModeBt;
    private EditText mWifiNameEt;
    /**
     * WEP
     */
    private RadioButton mWepRb;
    /**
     * WPA
     */
    private RadioButton mWpaRb;
    /**
     * NOPASS
     */
    private RadioButton mNopassRb;
    private RadioGroup mEncryptionTypeRg;
    private EditText mWifiPwdEt;
    /**
     * 提交
     */
    private Button mCommitWifiBt;
    private EditText mWifiHotNameEt;
    private EditText mWifiHotPwdEt;
    /**
     * 提交
     */
    private Button mCommitWifiHotBt;
    private RadioGroup mSoundModeRg;
    /**
     * 保存
     */
    private Button mSoundModeBt;

    /**
     * 后加的
     */

    private RadioGroup radioGrouptets;
    private RadioButton radioButtonwifitest;
    private RadioButton radioButtonwifihottest;
    private RadioButton radioButtonwifinull;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.investigation_terminal_layout);
        initView();

    }
    public void savayes(View view){
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }

    /**
    *上报时间保存
    *
    */
    public void shangbaotimesave(View  view){
       String str= sBao_timer.getText().toString().trim();
        if (!TextUtils.isEmpty(str)){
            writeDateToLocalFile("shangbao_timer",str);
            Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
        }else {
            writeDateToLocalFile("shangbao_timer","0");
            Toast.makeText(this, "请输入有效时间", Toast.LENGTH_SHORT).show();
        }
    }
    private void initView() {

        String Ip = writeEmptyContentToLocalFile("Ip");
        String Port = writeEmptyContentToLocalFile("Port");
        String IntervalTime = writeEmptyContentToLocalFile("IntervalTime");
        String DbValues = writeEmptyContentToLocalFile("DbValues");
        String CloseFlyModeTime = writeEmptyContentToLocalFile("CloseFlyModeTime");
        String WifiName = writeEmptyContentToLocalFile("WifiName");
        String EncryptionType = writeEmptyContentToLocalFile("EncryptionType");
        String WifiPwd = writeEmptyContentToLocalFile("WifiPwd");
        String WifiHotName = writeEmptyContentToLocalFile("WifiHotName");
        String WifiHotPwd = writeEmptyContentToLocalFile("WifiHotPwd");
        String SoundMode = writeEmptyContentToLocalFile("SoundMode");
        String wifimodle = writeEmptyContentToLocalFile("wifimodle");
        String Sbtimer = writeEmptyContentToLocalFile("shangbao_timer");


        sBao_timer= (EditText) findViewById(R.id.shangbao_timer);
        radioGrouptets = (RadioGroup) findViewById(R.id.startwifiviewgrouptest);
//        radioButtonwifitest = (RadioButton) findViewById(R.id.startwifitest);
        radioButtonwifihottest = (RadioButton) findViewById(R.id.starthotwifitest);
        radioButtonwifinull = (RadioButton) findViewById(R.id.starthotwifitestnull);

        String asas = ProperUtil.getConfigProperties("wifimodle");

        if (asas != null) {
//            if (asas.equals("connetedWifi")) {
//                radioButtonwifitest.setChecked(true);
//            }
            if (asas.equals("connetedWifiHot")) {
                radioButtonwifihottest.setChecked(true);
            }if (asas.equals("")) {
                radioButtonwifinull.setChecked(true);
            }


        } else {
            radioButtonwifinull.setChecked(true);
        }


        radioGrouptets.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
//                if (checkedId == R.id.startwifitest) {
//                    writeDateToLocalFile("wifimodle", "connetedWifi");
//                    Toast.makeText(InvestigationTerminalActivity.this, "选中的wifi", Toast.LENGTH_SHORT).show();
//                }
                if (checkedId == R.id.starthotwifitest) {
                    writeDateToLocalFile("wifimodle", "connetedWifiHot");
                    Toast.makeText(InvestigationTerminalActivity.this, "选中的热点", Toast.LENGTH_SHORT).show();
                }
                if (checkedId == R.id.starthotwifitestnull) {
                    writeDateToLocalFile("wifimodle", "");
                }
            }
        });

        String ipip = ProperUtil.getConfigProperties("Ip");
        mIPEt = (EditText) findViewById(R.id.IP_et);
        if (ipip!=null){
        mIPEt.setText(ipip);

        }else{
            mIPEt.setText("ss");
        }
        mPortNoEt = (EditText) findViewById(R.id.port_no_et);
        mPortNoEt.setText(Port);
        mUploadIntervalTimeEt = (EditText) findViewById(R.id.upload_interval_time_et);
        mUploadIntervalTimeEt.setText(IntervalTime);
        mCommitIPBt = (Button) findViewById(R.id.commit_IP_bt);
        mCommitIPBt.setOnClickListener(this);
        mDbCeilingEt = (EditText) findViewById(R.id.db_ceiling_et);
        mDbCeilingEt.setText(DbValues);
        mClosePlayModeTimeEt = (EditText) findViewById(R.id.closePlayModeTime_et);
        mClosePlayModeTimeEt.setText(CloseFlyModeTime);
        mCommitFlyModeBt = (Button) findViewById(R.id.commit_fly_mode_bt);
        mCommitFlyModeBt.setOnClickListener(this);
//        mWifiNameEt = (EditText) findViewById(R.id.wifi_name_et);
//        mWifiNameEt.setText(WifiName);
//        mWepRb = (RadioButton) findViewById(R.id.wep_rb);
//        mWpaRb = (RadioButton) findViewById(R.id.wpa_rb);
//        mNopassRb = (RadioButton) findViewById(R.id.nopass_rb);
//        mEncryptionTypeRg = (RadioGroup) findViewById(R.id.Encryption_type_rg);
//        if (EncryptionType.equals("WEP")) {
//            mEncryptionTypeRg.check(R.id.wep_rb);
//        } else if (EncryptionType.equals("WPA")) {
//            mEncryptionTypeRg.check(R.id.wpa_rb);
//        } else {
//            mEncryptionTypeRg.check(R.id.nopass_rb);
//        }

//        mWifiPwdEt = (EditText) findViewById(R.id.wifi_pwd_et);
//        mWifiPwdEt.setText(WifiPwd);
//        mCommitWifiBt = (Button) findViewById(R.id.commit_wifi_bt);
//        mCommitWifiBt.setOnClickListener(this);
        mWifiHotNameEt = (EditText) findViewById(R.id.wifi_hot_name_et_xzaxxzxc);
        mWifiHotNameEt.setText(WifiHotName);
        mWifiHotPwdEt = (EditText) findViewById(R.id.wifi_hot_pwd_et);
        mWifiHotPwdEt.setText(WifiHotPwd);
        mCommitWifiHotBt = (Button) findViewById(R.id.commit_wifi_hot_bt);
        mCommitWifiHotBt.setOnClickListener(this);

        mSoundModeRg = (RadioGroup) findViewById(R.id.sound_mode_rg);
        if (SoundMode.equals("SLIENCE")) {
            mSoundModeRg.check(R.id.silence_rb);
        } else if (SoundMode.equals("SHAKE")) {
            mSoundModeRg.check(R.id.shake_rb);
        }
        mSoundModeBt = (Button) findViewById(R.id.sound_mode_bt);
        mSoundModeBt.setOnClickListener(this);
    }

    private String writeEmptyContentToLocalFile(String str) {
        String Str = getConfigProperties(str);
        if (Str == null || TextUtils.isEmpty(Str)) {
            writeDateToLocalFile(str, "");
            return "";
        }
        return Str;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.commit_IP_bt:
                String mIPEtContent = mIPEt.getText().toString().trim();
                String mPortNoContent = mPortNoEt.getText().toString().trim();
                String mUploadIntervalTimeContent = mUploadIntervalTimeEt.getText().toString().trim();
                if (TextUtils.isEmpty(mIPEtContent) || mIPEtContent == null) {
                    Toast.makeText(this, "请将IP地址完善", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (!isIPAddress(mIPEtContent)) {
                        Toast.makeText(this, "IP格式有误，请重新输入", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                if (TextUtils.isEmpty(mPortNoContent) || mPortNoContent == null) {
                    Toast.makeText(this, "请将端口号完善", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (Integer.parseInt(mPortNoContent) < 0) {
                        Toast.makeText(this, "端口号不能为负数，请重新输入", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                if (TextUtils.isEmpty(mUploadIntervalTimeContent) || mUploadIntervalTimeContent == null) {
                    Toast.makeText(this, "请将时间间隔完善", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (Integer.parseInt(mUploadIntervalTimeContent) < 0) {
                        Toast.makeText(this, "时间间隔不能为负数，请重新输入", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                writeDateToLocalFile("Ip", mIPEtContent);
                writeDateToLocalFile("Port", mPortNoContent);
                writeDateToLocalFile("IntervalTime", mUploadIntervalTimeContent);
                Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.commit_fly_mode_bt:
                String mDbCeilingContent = mDbCeilingEt.getText().toString().trim();
                String mClosePlayModeTimeContent = mClosePlayModeTimeEt.getText().toString().trim();
                if (TextUtils.isEmpty(mDbCeilingContent) || mDbCeilingContent == null) {
                    Toast.makeText(this, "请填写分贝上限", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (Integer.parseInt(mDbCeilingContent) < 0) {
                        Toast.makeText(this, "分贝值不能为负", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                if (TextUtils.isEmpty(mClosePlayModeTimeContent) || mClosePlayModeTimeContent == null) {
                    Toast.makeText(this, "请将时间完善", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (Integer.parseInt(mClosePlayModeTimeContent) < 0) {
                        Toast.makeText(this, "时间不能为负数，请重新输入", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                writeDateToLocalFile("DbValues", mDbCeilingContent);
                writeDateToLocalFile("CloseFlyModeTime", mClosePlayModeTimeContent);
                Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
                break;
//            case R.id.commit_wifi_bt:
//                String mWifiNameContent = mWifiNameEt.getText().toString().trim();
//                String mWifiPwdContent = mWifiPwdEt.getText().toString().trim();
//                if (TextUtils.isEmpty(mWifiNameContent) || mWifiNameContent == null) {
//                    Toast.makeText(this, "请填写Wifi名称", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (TextUtils.isEmpty(mWifiPwdContent) || mWifiPwdContent == null) {
//                    Toast.makeText(this, "请填写Wifi密码", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                int i = mEncryptionTypeRg.getCheckedRadioButtonId();
//                if (i == R.id.wep_rb) {
//                    writeDateToLocalFile("EncryptionType", "WEP");
//                } else if (i == R.id.wpa_rb) {
//                    writeDateToLocalFile("EncryptionType", "WPA");
//                } else if (i == R.id.nopass_rb) {
//                    writeDateToLocalFile("EncryptionType", "NOPASS");
//                }
//                writeDateToLocalFile("WifiName", mWifiNameContent);
//                writeDateToLocalFile("WifiPwd", mWifiPwdContent);
//                Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
//                break;
            case R.id.commit_wifi_hot_bt:
                String mWifiHotNameContent = mWifiHotNameEt.getText().toString().trim();
                String mWifiHotPwdContent = mWifiHotPwdEt.getText().toString().trim();
                if (TextUtils.isEmpty(mWifiHotNameContent) || mWifiHotNameContent == null) {
                    Toast.makeText(this, "请填写Wifi热点名称", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(mWifiHotPwdContent) || mWifiHotPwdContent == null || mWifiHotPwdContent.length() < 8) {
                    Toast.makeText(this, "请填写Wifi热点密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                writeDateToLocalFile("WifiHotName", mWifiHotNameContent);
                writeDateToLocalFile("WifiHotPwd", mWifiHotPwdContent);
                Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sound_mode_bt:
                int x = mSoundModeRg.getCheckedRadioButtonId();
                if (x == R.id.silence_rb) {
                    writeDateToLocalFile("SoundMode", "SLIENCE");
                } else if (x == R.id.shake_rb) {
                    writeDateToLocalFile("SoundMode", "SHAKE");
                }
                Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
