package com.appsdaily.payment.error;

import java.util.HashMap;

import com.onward.scm.exceptions.AppsDailyErrorCode;

public class PciPaymentErrorCode extends AppsDailyErrorCode {

    //Ipayy ErrorCodes

    public static final HashMap< String, Integer > thirdPartytoAppsDailyMap=new HashMap< String, Integer >();
    static{
        thirdPartytoAppsDailyMap.put("IRQ0101",4100);
        thirdPartytoAppsDailyMap.put("IRQ0102",4101);
        thirdPartytoAppsDailyMap.put("IRQ0103",4102);
        thirdPartytoAppsDailyMap.put("IRQ0104",4103);
        thirdPartytoAppsDailyMap.put("IRQ0105",4104);
        thirdPartytoAppsDailyMap.put("IRQ0201",4105);
        thirdPartytoAppsDailyMap.put("IRQ0401",4106);
        thirdPartytoAppsDailyMap.put("IRQ0601",4107);
        thirdPartytoAppsDailyMap.put("ICR0101",4108);
        thirdPartytoAppsDailyMap.put("ICR0201",4109);
        thirdPartytoAppsDailyMap.put("ICR0301",4110);
        thirdPartytoAppsDailyMap.put("ICR0401",4111);
        thirdPartytoAppsDailyMap.put("ISC0101",4112);
        thirdPartytoAppsDailyMap.put("ISC0201",4113);
        thirdPartytoAppsDailyMap.put("ISC0301",4114);
        thirdPartytoAppsDailyMap.put("SER0101",4115);
        thirdPartytoAppsDailyMap.put("SER0102",4116);
        thirdPartytoAppsDailyMap.put("SER0201",4117);
        thirdPartytoAppsDailyMap.put("SER0401",4118);
        thirdPartytoAppsDailyMap.put("SER0501",4119);
        thirdPartytoAppsDailyMap.put("SER0701",4120);
        thirdPartytoAppsDailyMap.put("SER1301",4121);
        thirdPartytoAppsDailyMap.put("SER1501",4122);
        thirdPartytoAppsDailyMap.put("UXE0001",4123);
        thirdPartytoAppsDailyMap.put("CFG0101",4124);
        thirdPartytoAppsDailyMap.put("CFG0102",4125);
        thirdPartytoAppsDailyMap.put("CFG0201",4126);
        thirdPartytoAppsDailyMap.put("CFG0301",4127);
        thirdPartytoAppsDailyMap.put("CFG0401",4128);
        thirdPartytoAppsDailyMap.put("ERROR002",4103);
        thirdPartytoAppsDailyMap.put("ERROR003",4103);
        thirdPartytoAppsDailyMap.put("ERROR005",4103);
        thirdPartytoAppsDailyMap.put("ERROR006",4103);
        thirdPartytoAppsDailyMap.put("ERROR008",4103);
        thirdPartytoAppsDailyMap.put("ERROR009",4103);
        thirdPartytoAppsDailyMap.put("ERROR022",4103);
        thirdPartytoAppsDailyMap.put("ERROR023",4103);
        thirdPartytoAppsDailyMap.put("ERROR027",4103);
        thirdPartytoAppsDailyMap.put("ERROR033",4103);
        thirdPartytoAppsDailyMap.put("ERROR035",4103);
        thirdPartytoAppsDailyMap.put("ERROR037",4101);
        thirdPartytoAppsDailyMap.put("ERROR039",4103);
        thirdPartytoAppsDailyMap.put("ERROR061",4101);
        thirdPartytoAppsDailyMap.put("ERROR063",4103);
        thirdPartytoAppsDailyMap.put("ERROR064",4101);
        thirdPartytoAppsDailyMap.put("ERROR065",4103);
        thirdPartytoAppsDailyMap.put("ERROR066",4103);
        thirdPartytoAppsDailyMap.put("ERROR067",4103);
        thirdPartytoAppsDailyMap.put("ERROR068",4103);
        thirdPartytoAppsDailyMap.put("ERROR069",4103);
        thirdPartytoAppsDailyMap.put("ERROR070",4103);
        thirdPartytoAppsDailyMap.put("ERROR071",4103);
        thirdPartytoAppsDailyMap.put("ERROR072",4103);
        thirdPartytoAppsDailyMap.put("ERROR073",4015);
        thirdPartytoAppsDailyMap.put("0392",4129);
        thirdPartytoAppsDailyMap.put("0395",4130);
        thirdPartytoAppsDailyMap.put("0396",4131);
        thirdPartytoAppsDailyMap.put("0397",4132);
        thirdPartytoAppsDailyMap.put("0399",4133);
        thirdPartytoAppsDailyMap.put("0400",4134);
        thirdPartytoAppsDailyMap.put("0401",4134);
        thirdPartytoAppsDailyMap.put("0402",4134);
        thirdPartytoAppsDailyMap.put("0499",4135);
        thirdPartytoAppsDailyMap.put("9999",4016);
        thirdPartytoAppsDailyMap.put("ERROR074",4103);
        thirdPartytoAppsDailyMap.put("ERROR075",4103);
        thirdPartytoAppsDailyMap.put("ERROR077",4103);
        thirdPartytoAppsDailyMap.put("ERROR078",4101);
        thirdPartytoAppsDailyMap.put("ERROR079",4101);
        thirdPartytoAppsDailyMap.put("ERROR080",4101);
        thirdPartytoAppsDailyMap.put("ERROR081",4103);
        thirdPartytoAppsDailyMap.put("ERROR082",4103);
        thirdPartytoAppsDailyMap.put("ERROR083",4103);
        thirdPartytoAppsDailyMap.put("ERROR084",4103);
        thirdPartytoAppsDailyMap.put("ERROR085",4136);
        thirdPartytoAppsDailyMap.put("ERROR086",4137);
        thirdPartytoAppsDailyMap.put("ERROR087",4138);
        thirdPartytoAppsDailyMap.put("ERROR088",4139);
        thirdPartytoAppsDailyMap.put("ERROR089",4140);
        thirdPartytoAppsDailyMap.put("ERROR090",4141);
        thirdPartytoAppsDailyMap.put("ERROR000",4142);
        thirdPartytoAppsDailyMap.put("ERROR091",4143);
        thirdPartytoAppsDailyMap.put("ERROR0101",4144);
        thirdPartytoAppsDailyMap.put("ERROR0102",4145);
        thirdPartytoAppsDailyMap.put("ERROR0103",4103);
        thirdPartytoAppsDailyMap.put("ERROR0104",4103);
        thirdPartytoAppsDailyMap.put("ERROR0105",4103);
        thirdPartytoAppsDailyMap.put("ERROR0106",4146);
        thirdPartytoAppsDailyMap.put("ERROR0107",4147);
        thirdPartytoAppsDailyMap.put("ERROR0108",4148);
        thirdPartytoAppsDailyMap.put("ERROR300",4149);
        thirdPartytoAppsDailyMap.put("ERROR301",4150);
        thirdPartytoAppsDailyMap.put("ERROR302",4151);
        thirdPartytoAppsDailyMap.put("ERROR303",4152);
        thirdPartytoAppsDailyMap.put("ERROR304",4153);
        thirdPartytoAppsDailyMap.put("ERROR305",4154);
        thirdPartytoAppsDailyMap.put("ERROR306",4155);
        thirdPartytoAppsDailyMap.put("ERROR307",4155);
        thirdPartytoAppsDailyMap.put("ERROR308",4155);
        thirdPartytoAppsDailyMap.put("ERROR0109",4008);
        thirdPartytoAppsDailyMap.put("ERROR0110",4103);
        thirdPartytoAppsDailyMap.put("ERROR0111",4156);
        thirdPartytoAppsDailyMap.put("ERROR0112",4157);
        thirdPartytoAppsDailyMap.put("ERROR0113",4158);
        thirdPartytoAppsDailyMap.put("ERROR_PAYNIMO_001",4003);
        thirdPartytoAppsDailyMap.put("ERROR_PAYNIMO_002",4003);
        thirdPartytoAppsDailyMap.put("ERROR_PAYNIMO_003",4003);
        thirdPartytoAppsDailyMap.put("ERROR_PAYNIMO_004",4003);
        thirdPartytoAppsDailyMap.put("ERROR_PAYNIMO_005",4003);
        thirdPartytoAppsDailyMap.put("ERROR_PAYNIMO_006",4003);
        thirdPartytoAppsDailyMap.put("ERROR_PAYNIMO_007",4003);
        thirdPartytoAppsDailyMap.put("ERROR_PAYNIMO_008",4003);
        thirdPartytoAppsDailyMap.put("ERROR_PAYNIMO_009",4003);
        thirdPartytoAppsDailyMap.put("ERROR_PAYNIMO_010",4003);
        thirdPartytoAppsDailyMap.put("LOW_BALANCE",4123);
        thirdPartytoAppsDailyMap.put("INVALID_SUBSCRIBER",4123);
        thirdPartytoAppsDailyMap.put("FREQUENCY_THRESHOLD_VIOLATED",4123);
        thirdPartytoAppsDailyMap.put("OPERATOR_SERVICE_FAILURE",4123);
        thirdPartytoAppsDailyMap.put("AMOUNT_THRESHOLD_VIOLATED",4123);
        thirdPartytoAppsDailyMap.put("COCP_VALIDATION_ERROR",4123);
        thirdPartytoAppsDailyMap.put("NOT_PROCESSED",4123);
        thirdPartytoAppsDailyMap.put("PRICEPOINT_UNSUPPORTED",4123);
        thirdPartytoAppsDailyMap.put("INTERNAL_SERVICE_FAILURE",4123);
        thirdPartytoAppsDailyMap.put("SER0301",4123);
        thirdPartytoAppsDailyMap.put("SER0302",4123);
        thirdPartytoAppsDailyMap.put("SER0303",4123);
        thirdPartytoAppsDailyMap.put("SER0901",4123);
        thirdPartytoAppsDailyMap.put("SER1101",4123);
        thirdPartytoAppsDailyMap.put("SUB0101",4123);
        thirdPartytoAppsDailyMap.put("SUB0201",4123);
        thirdPartytoAppsDailyMap.put("SUB0301",4123);
        thirdPartytoAppsDailyMap.put("SUB0401",4123);
        thirdPartytoAppsDailyMap.put("SUB0501",4123);//TODO
    }

    // Transaction Failed

    // ---------------------------------------------------------------------//

    public static final long TRANSACTION_SYSTEM_ERROR_CODE = 4001;
    public static final long USER_CANCEL_CODE = 4002;
    public static final long TECHNICAL_ISSUE_CODE = 4003;
    public static final long INVALID_ACCESS_CODE = 4004;
    public static final long USER_AUTHENTICATION_FAILED = 4005;
    public static final long INVALID_SCRATCHCARD_CODE = 4006;
    public static final long LOW_BALANCE = 4007;
    public static final long SESSION_EXPIRED_CODE = 4008;
    public static final long BANK_ERROR_CODE = 4009;
    public static final long INVALID_PARTNER_CODE = 4010;
    public static final long UNABLE_TO_PARSE = 4011;
    public static final long TRANSACTION_INITIALIZE_CODE = 4014;
    public static final long TRANSACTION_ID_FORMAT_ERROR_CODE = 4015;
    public static final long TRANSACTION_NOT_EXIST_CODE = 4016;
    public static final long INCOMPLETE_TRANSACTION_CODE =4000;
    public static final long UNEXPECTED_ERROR_CODE =4123;

    //IPAYYERROR CODES FROM 41XX









}
