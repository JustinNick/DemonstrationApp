package com.example.it_ch.demonstrationapp.ui.activity.homepage;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.citylink.tsm.citycard.CityCompatible;
import com.citylink.tsm.citycard.bean.TradeRecord;
import com.citylinkdata.cardnfc.BaseCityCard;
import com.citylinkdata.cardnfc.NFCCardManager;
import com.example.it_ch.demonstrationapp.R;

import java.util.ArrayList;


public class SingleTicketTestActivity extends AppCompatActivity {
    private static final String TAG = "SingleTicketTestActivit";
    public static final String CITY_CODE = "4250";
    private ImageView mIvNfcSinga,mIvMoveCard;
    private ImageView mIvBack;
    private NfcAdapter nfcAdapter;
    private NfcAdapter mNfcAdapter;
    private PendingIntent pendingIntent;
    private Intent mTagIntent;
    private ArrayList<TradeRecord> mChargeRecordList = null;//充值记录
    private ArrayList<TradeRecord> mLocalRecordList=null;//消费记录

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_ticket_test);
        immersion();
        checkNFC();
        initViews();
        initEvents();
    }

    private void initViews(){
        ((TextView)findViewById(R.id.tv_title_bar)).setText("单程票验票");
        mIvBack = (ImageView) findViewById(R.id.iv_left_back);
        mIvNfcSinga = (ImageView) findViewById(R.id.nfc_signal_imageview);
        mIvMoveCard = (ImageView) findViewById(R.id.move_card_imageview);
        startCardAnimal();
        startNfcSingalAnimal();
        if (checkNFC()){
            //设备NFC接入
            nfcAdapter = NfcAdapter.getDefaultAdapter(getBaseContext());
            Intent intent = new Intent(this,getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent = PendingIntent.getActivity(this.getBaseContext(),0,intent,0);
            mTagIntent = getIntent();
            onNewIntent(getIntent());
        }

    }

    private void initEvents(){
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /**
     * 开启nfc搜索信号动画  帧动画
     */
    private void startNfcSingalAnimal() {
        AnimationDrawable nfcSingaAnimDrawble = (AnimationDrawable) mIvNfcSinga.getDrawable();
        nfcSingaAnimDrawble.start();
    }


    /**
     * 开启充值卡移动动画
     */
    private void startCardAnimal() {
        final AnimationSet animationSet = (AnimationSet) AnimationUtils.loadAnimation(this, R.anim.translate);

        mIvMoveCard.startAnimation(animationSet);
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                mIvMoveCard.startAnimation(animationSet);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
    }

    @Override
    public void onNewIntent(Intent intent) {
            Log.e(TAG,"接收到读卡动作");
            mTagIntent = intent;
            //2155常熟
            NFCCardManager.initNFCCard(mTagIntent,CITY_CODE,null);
            showNFCData();
    }

    private void showNFCData(){
        String[] arg0 = {BaseCityCard.CARD_BLANCE, BaseCityCard.CARD_NUM,BaseCityCard.CARD_DATE,
                BaseCityCard.CARD_SERIAL,BaseCityCard.CARD_LOCAL_RECODE,BaseCityCard.CARD_CHARGE_RECODE,BaseCityCard.CARD_REMOTE_RECODE,"card_enabled"};
        Bundle cardundle = getCardInfo(arg0);
        String preBalance = cardundle.getString(BaseCityCard.CARD_BLANCE);
        String cardNum= cardundle.getString(BaseCityCard.CARD_NUM);
        String appSerial = cardundle.getString(BaseCityCard.CARD_SERIAL);
        String cardExtendDate = cardundle.getString(BaseCityCard.CARD_DATE);
        boolean cardEnabled = cardundle.getBoolean("card_enabled");
        if(!cardEnabled){
            Toast.makeText(this,"此卡已经被停用或者该卡不属于该城市通卡。",Toast.LENGTH_SHORT).show();
            return;
        }
        mChargeRecordList=cardundle.getParcelableArrayList(BaseCityCard.CARD_CHARGE_RECODE);
        mLocalRecordList = cardundle.getParcelableArrayList(BaseCityCard.CARD_LOCAL_RECODE);

        if(cardNum != null  && preBalance != null) {
            cardNum = ((CityCompatible) getBaseCityCard()).getCardNum(cardNum);
            String balance = ((CityCompatible) getBaseCityCard()).getCardBalance(preBalance);
            StringBuffer buffer = new StringBuffer();

            buffer.append("卡号：" + cardNum + "\n");
            buffer.append("余额：" + balance + "\n");
            buffer.append("充值数量：" + mChargeRecordList.size());
            //buffer.append("消费数量：" + mLocalRecordList.size());
//            Toast.makeText(this, buffer.toString(), Toast.LENGTH_SHORT).show();
            //mChargeRecordList.addAll(mLocalRecordList);
            Intent intent=new Intent(this,CardRecordActivity.class);
            intent.putExtra("Card_Num",cardNum);
            intent.putExtra("Card_Balance",balance);
            intent.putExtra("Card_ExtendDate",cardExtendDate);
            intent.putExtra("IsBle",false);
            intent.putParcelableArrayListExtra("Card_Logs",mChargeRecordList);
            //intent.putParcelableArrayListExtra("Card_Local_Records",mLocalRecordList);
            startActivity(intent);
        }else{
            Toast.makeText(this, "并非所属城市的卡片。", Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * 获取某城市对于IC还是SIM卡
     * @return
     */
    public BaseCityCard getBaseCityCard(){
        BaseCityCard baseCity  = null;
        if(NFCCardManager.isNFCardIcTag() || NFCCardManager.isIcFlag()){
            baseCity = NFCCardManager.getCityCard(); //IC卡
        }
        return baseCity;
    }
    /**
     * 获取卡上信息
     * @param key
     * @return
     */
    public Bundle getCardInfo(String[] key){
        BaseCityCard cityCard = getBaseCityCard();
        if(cityCard ==null)
            return new Bundle();
        else {
            Message msg = cityCard.getCardInfo(key);
            return msg.getData();
        }
    }

    private boolean checkNFC()
    {
        this.mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (this.mNfcAdapter == null)
        {
            Toast.makeText(this,"设备不支持NFC功能！",Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!this.mNfcAdapter.isEnabled())
        {
            Toast.makeText(this,"请在系统设置中先启用NFC功能！",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void immersion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(nfcAdapter!=null){
            nfcAdapter.enableForegroundDispatch(this,pendingIntent, NFCCardManager.FILTERS,NFCCardManager.TECHLISTS);
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        if(nfcAdapter!=null){
            nfcAdapter.disableForegroundDispatch(this);
        }
    }
}
