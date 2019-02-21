package com.example.hardware.Util;

import android.content.Context;
import android.telephony.TelephonyManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.ObjectInput;
import java.io.Reader;

import static android.telephony.TelephonyManager.SIM_STATE_ABSENT;
import static android.telephony.TelephonyManager.SIM_STATE_NETWORK_LOCKED;
import static android.telephony.TelephonyManager.SIM_STATE_PIN_REQUIRED;
import static android.telephony.TelephonyManager.SIM_STATE_PUK_REQUIRED;
import static android.telephony.TelephonyManager.SIM_STATE_READY;
import static android.telephony.TelephonyManager.SIM_STATE_UNKNOWN;

/**
 * Created by zhangmp on 2018/5/31.
 */

public class SimCardUtils {
    public static String getNetworkOperatorName(Context argContext) {
        String networkOperatorName;
        try {
            TelephonyManager telephonyManager = (TelephonyManager)argContext.getSystemService(Context.TELEPHONY_SERVICE);
            networkOperatorName = telephonyManager.getNetworkOperatorName();
        }
        catch(Exception exception) {
            networkOperatorName = "";
        }

        return networkOperatorName;
    }

    public static String getCarrierName(Context argContext) {
        String networkOperatorName = getNetworkOperatorName(argContext).toLowerCase();
        String ret = "none";
        if((networkOperatorName.equals("中国移动")) || (networkOperatorName.equals("china mobile")) || (networkOperatorName.equals("chinamobile"))) {
            ret = "China Mobile";
        }
        else if(networkOperatorName.equals("中国联通") || networkOperatorName.equals("china unicom") || networkOperatorName.equals("chinaunicom")) {
            ret = "China Unicom";
        }
        else if(networkOperatorName.equals("中国电信") || networkOperatorName.equals("china net") || networkOperatorName.equals("chinanet")) {
            ret = "China Net";
        }else{

        }

        return ret;
    }

    public static String getSimSerialNumber(Context context) {
        String simSerialNumber = "";
        TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        if(null != telephonyManager){
            simSerialNumber = telephonyManager.getSimSerialNumber();
        }
        return simSerialNumber;
    }

    public static String getSubscriberId(Context context){
        String subscriberId = "";
        TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        if(null != telephonyManager){
            subscriberId = telephonyManager.getSubscriberId();
        }
        return subscriberId;
    }

    public static String getNetworkOperator(Context context) {
        String  networkOperator = "";
        TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        if(null != telephonyManager){
            networkOperator = telephonyManager.getNetworkOperator();
        }
        return networkOperator;
    }

    public static String getMCC(String networkOperator){
        String mcc = "";
        if(!networkOperator.isEmpty()){
            mcc = networkOperator.substring(0,3);
        }
        return mcc;
    }

    public static String getMNC(String networkOperator){
        String mnc = "";
        if(!networkOperator.isEmpty()){
            mnc = networkOperator.substring(3);
        }
        return mnc;
    }

    public static int getSimState(Context context){
        int simState = SIM_STATE_UNKNOWN;
        TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        if(null != telephonyManager){
            simState = telephonyManager.getSimState();
        }
        return simState;
    }

    public static String getSimStateDescription(int simState){
        String simStateDescription = "";
        switch (simState){
            case SIM_STATE_UNKNOWN:{
                simStateDescription = "SIM_STATE_UNKNOWN";
            }
            break;

            case SIM_STATE_ABSENT:{
                /** SIM card state: no SIM card is available in the device */
                simStateDescription = "SIM_STATE_ABSENT";
            }
            break;

            case SIM_STATE_PIN_REQUIRED:{
                /** SIM card state: Locked: requires the user's SIM PIN to unlock */
                simStateDescription = "SIM_STATE_PIN_REQUIRED";
            }
            break;

            case SIM_STATE_PUK_REQUIRED:{
                /** SIM card state: Locked: requires the user's SIM PUK to unlock */
                simStateDescription = "SIM_STATE_PUK_REQUIRED";
            }
            break;

            case SIM_STATE_NETWORK_LOCKED:{
                /** SIM card state: Locked: requires a network PIN to unlock */
                simStateDescription = "SIM_STATE_NETWORK_LOCKED";
            }
            break;

            case SIM_STATE_READY:{
                /** SIM card state: Ready */
                simStateDescription = "SIM_STATE_READY";
            }
            break;

            case 6:{
                /** SIM card state: SIM Card is NOT READY*/
                simStateDescription = "SIM_STATE_NOT_READY";
            }
            break;

            case 7:{
                /** SIM card state: SIM Card Error, permanently disabled*/
                simStateDescription = "SIM_STATE_PERM_DISABLED";
            }
            break;

            case 8:{
                /** SIM card state: , present but faulty*/
                simStateDescription = "SIM_STATE_CARD_IO_ERROR";
            }
            break;
        }
        return simStateDescription;
    }

    public static void getInfo(Context context){
        String networkOperatorName = SimCardUtils.getNetworkOperatorName(context);
        LogUtils.i(String.format("SimCardUtils networkOperatorName=%s",networkOperatorName));

        String carrierName = SimCardUtils.getCarrierName(context);
        LogUtils.i(String.format("SimCardUtils carrierName=%s",carrierName));

        String simSerialNumber = SimCardUtils.getSimSerialNumber(context);
        LogUtils.i(String.format("SimCardUtils simSerialNumber=%s",simSerialNumber));

        int simState = SimCardUtils.getSimState(context);
        String simStateDescription = SimCardUtils.getSimStateDescription(simState);
        LogUtils.i(String.format("SimCardUtils simState= %d simStateDescription=%s",simState,simStateDescription));

        String networkOperator = SimCardUtils.getNetworkOperator(context);
        String mcc = SimCardUtils.getMCC(networkOperator);
        String mnc = SimCardUtils.getMNC(networkOperator);
        LogUtils.i(String.format("SimCardUtils networkOperator=%s mcc=%s mnc=%s",networkOperator,mcc,mnc));

        String subscriberId = SimCardUtils.getSubscriberId(context);
        LogUtils.i(String.format("SimCardUtils subscriberId=%s",subscriberId));
    }

}
