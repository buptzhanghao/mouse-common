package com.mouse.common.domain;

/**
 * @author zhanghao
 * @version 1.0
 * @created 16/7/11
 */
public class PyMap {

    public static String[] PINYIN;
    public static int[] PY_INDEX;
    public static int LENGTH = 397;

    static {
        PINYIN = new String[LENGTH];
        PY_INDEX = new int[LENGTH];
        PINYIN[0] = "a";
        PY_INDEX[0] = 20319;
        PINYIN[1] = "ai";
        PY_INDEX[1] = 20317;
        PINYIN[2] = "an";
        PY_INDEX[2] = 20304;
        PINYIN[3] = "ang";
        PY_INDEX[3] = 20295;
        PINYIN[4] = "ang";
        PY_INDEX[4] = 20295;
        PINYIN[5] = "ao";
        PY_INDEX[5] = 20292;
        PINYIN[6] = "ba";
        PY_INDEX[6] = 20283;
        PINYIN[7] = "bai";
        PY_INDEX[7] = 20265;
        PINYIN[8] = "ban";
        PY_INDEX[8] = 20257;
        PINYIN[9] = "bang";
        PY_INDEX[9] = 20242;
        PINYIN[10] = "bao";
        PY_INDEX[10] = 20230;
        PINYIN[11] = "bei";
        PY_INDEX[11] = 20051;
        PINYIN[12] = "ben";
        PY_INDEX[12] = 20036;
        PINYIN[13] = "beng";
        PY_INDEX[13] = 20032;
        PINYIN[14] = "bi";
        PY_INDEX[14] = 20026;
        PINYIN[15] = "bian";
        PY_INDEX[15] = 20002;
        PINYIN[16] = "biao";
        PY_INDEX[16] = 19990;
        PINYIN[17] = "bie";
        PY_INDEX[17] = 19986;
        PINYIN[18] = "bin";
        PY_INDEX[18] = 19982;
        PINYIN[19] = "bing";
        PY_INDEX[19] = 19976;
        PINYIN[20] = "bo";
        PY_INDEX[20] = 19805;
        PINYIN[21] = "bu";
        PY_INDEX[21] = 19784;
        PINYIN[22] = "ca";
        PY_INDEX[22] = 19775;
        PINYIN[23] = "cai";
        PY_INDEX[23] = 19774;
        PINYIN[24] = "can";
        PY_INDEX[24] = 19763;
        PINYIN[25] = "cang";
        PY_INDEX[25] = 19756;
        PINYIN[26] = "cao";
        PY_INDEX[26] = 19751;
        PINYIN[27] = "ce";
        PY_INDEX[27] = 19746;
        PINYIN[28] = "ceng";
        PY_INDEX[28] = 19741;
        PINYIN[29] = "cha";
        PY_INDEX[29] = 19739;
        PINYIN[30] = "chai";
        PY_INDEX[30] = 19728;
        PINYIN[31] = "chan";
        PY_INDEX[31] = 19725;
        PINYIN[32] = "chang";
        PY_INDEX[32] = 19715;
        PINYIN[33] = "chao";
        PY_INDEX[33] = 19540;
        PINYIN[34] = "che";
        PY_INDEX[34] = 19531;
        PINYIN[35] = "chen";
        PY_INDEX[35] = 19525;
        PINYIN[36] = "cheng";
        PY_INDEX[36] = 19515;
        PINYIN[37] = "chi";
        PY_INDEX[37] = 19500;
        PINYIN[38] = "chong";
        PY_INDEX[38] = 19484;
        PINYIN[39] = "chou";
        PY_INDEX[39] = 19479;
        PINYIN[40] = "chu";
        PY_INDEX[40] = 19467;
        PINYIN[41] = "chuai";
        PY_INDEX[41] = 19289;
        PINYIN[42] = "chuan";
        PY_INDEX[42] = 19288;
        PINYIN[43] = "chuang";
        PY_INDEX[43] = 19281;
        PINYIN[44] = "chui";
        PY_INDEX[44] = 19275;
        PINYIN[45] = "chun";
        PY_INDEX[45] = 19270;
        PINYIN[46] = "chuo";
        PY_INDEX[46] = 19263;
        PINYIN[47] = "ci";
        PY_INDEX[47] = 19261;
        PINYIN[48] = "cong";
        PY_INDEX[48] = 19249;
        PINYIN[49] = "cou";
        PY_INDEX[49] = 19243;
        PINYIN[50] = "cu";
        PY_INDEX[50] = 19242;
        PINYIN[51] = "cuan";
        PY_INDEX[51] = 19238;
        PINYIN[52] = "cui";
        PY_INDEX[52] = 19235;
        PINYIN[53] = "cun";
        PY_INDEX[53] = 19227;
        PINYIN[54] = "cuo";
        PY_INDEX[54] = 19224;
        PINYIN[55] = "da";
        PY_INDEX[55] = 19218;
        PINYIN[56] = "dai";
        PY_INDEX[56] = 19212;
        PINYIN[57] = "dan";
        PY_INDEX[57] = 19038;
        PINYIN[58] = "dang";
        PY_INDEX[58] = 19023;
        PINYIN[59] = "dao";
        PY_INDEX[59] = 19018;
        PINYIN[60] = "de";
        PY_INDEX[60] = 19006;
        PINYIN[61] = "deng";
        PY_INDEX[61] = 19003;
        PINYIN[62] = "di";
        PY_INDEX[62] = 18996;
        PINYIN[63] = "dian";
        PY_INDEX[63] = 18977;
        PINYIN[64] = "diao";
        PY_INDEX[64] = 18961;
        PINYIN[65] = "die";
        PY_INDEX[65] = 18952;
        PINYIN[66] = "ding";
        PY_INDEX[66] = 18783;
        PINYIN[67] = "diu";
        PY_INDEX[67] = 18774;
        PINYIN[68] = "dong";
        PY_INDEX[68] = 18773;
        PINYIN[69] = "dou";
        PY_INDEX[69] = 18763;
        PINYIN[70] = "du";
        PY_INDEX[70] = 18756;
        PINYIN[71] = "duan";
        PY_INDEX[71] = 18741;
        PINYIN[72] = "dui";
        PY_INDEX[72] = 18735;
        PINYIN[73] = "dun";
        PY_INDEX[73] = 18731;
        PINYIN[74] = "duo";
        PY_INDEX[74] = 18722;
        PINYIN[75] = "e";
        PY_INDEX[75] = 18710;
        PINYIN[76] = "en";
        PY_INDEX[76] = 18697;
        PINYIN[77] = "er";
        PY_INDEX[77] = 18696;
        PINYIN[78] = "fa";
        PY_INDEX[78] = 18526;
        PINYIN[79] = "fan";
        PY_INDEX[79] = 18518;
        PINYIN[80] = "fang";
        PY_INDEX[80] = 18501;
        PINYIN[81] = "fei";
        PY_INDEX[81] = 18490;
        PINYIN[82] = "fen";
        PY_INDEX[82] = 18478;
        PINYIN[83] = "feng";
        PY_INDEX[83] = 18463;
        PINYIN[84] = "fo";
        PY_INDEX[84] = 18448;
        PINYIN[85] = "fou";
        PY_INDEX[85] = 18447;
        PINYIN[86] = "fu";
        PY_INDEX[86] = 18446;
        PINYIN[87] = "ga";
        PY_INDEX[87] = 18239;
        PINYIN[88] = "gai";
        PY_INDEX[88] = 18237;
        PINYIN[89] = "gan";
        PY_INDEX[89] = 18231;
        PINYIN[90] = "gang";
        PY_INDEX[90] = 18220;
        PINYIN[91] = "gao";
        PY_INDEX[91] = 18211;
        PINYIN[92] = "ge";
        PY_INDEX[92] = 18201;
        PINYIN[93] = "gei";
        PY_INDEX[93] = 18184;
        PINYIN[94] = "gen";
        PY_INDEX[94] = 18183;
        PINYIN[95] = "geng";
        PY_INDEX[95] = 18181;
        PINYIN[96] = "gong";
        PY_INDEX[96] = 18012;
        PINYIN[97] = "gou";
        PY_INDEX[97] = 17997;
        PINYIN[98] = "gu";
        PY_INDEX[98] = 17988;
        PINYIN[99] = "gua";
        PY_INDEX[99] = 17970;
        PINYIN[100] = "guai";
        PY_INDEX[100] = 17964;
        PINYIN[101] = "guan";
        PY_INDEX[101] = 17961;
        PINYIN[102] = "guang";
        PY_INDEX[102] = 17950;
        PINYIN[103] = "gui";
        PY_INDEX[103] = 17947;
        PINYIN[104] = "gun";
        PY_INDEX[104] = 17931;
        PINYIN[105] = "guo";
        PY_INDEX[105] = 17928;
        PINYIN[106] = "ha";
        PY_INDEX[106] = 17922;
        PINYIN[107] = "hai";
        PY_INDEX[107] = 17759;
        PINYIN[108] = "han";
        PY_INDEX[108] = 17752;
        PINYIN[109] = "hang";
        PY_INDEX[109] = 17733;
        PINYIN[110] = "hao";
        PY_INDEX[110] = 17730;
        PINYIN[111] = "he";
        PY_INDEX[111] = 17721;
        PINYIN[112] = "hei";
        PY_INDEX[112] = 17703;
        PINYIN[113] = "hen";
        PY_INDEX[113] = 17701;
        PINYIN[114] = "heng";
        PY_INDEX[114] = 17697;
        PINYIN[115] = "hong";
        PY_INDEX[115] = 17692;
        PINYIN[116] = "hou";
        PY_INDEX[116] = 17683;
        PINYIN[117] = "hu";
        PY_INDEX[117] = 17676;
        PINYIN[118] = "hua";
        PY_INDEX[118] = 17496;
        PINYIN[119] = "huai";
        PY_INDEX[119] = 17487;
        PINYIN[120] = "huan";
        PY_INDEX[120] = 17482;
        PINYIN[121] = "huang";
        PY_INDEX[121] = 17468;
        PINYIN[122] = "hui";
        PY_INDEX[122] = 17454;
        PINYIN[123] = "hun";
        PY_INDEX[123] = 17433;
        PINYIN[124] = "huo";
        PY_INDEX[124] = 17427;
        PINYIN[125] = "ji";
        PY_INDEX[125] = 17417;
        PINYIN[126] = "jia";
        PY_INDEX[126] = 17202;
        PINYIN[127] = "jian";
        PY_INDEX[127] = 17185;
        PINYIN[128] = "jiang";
        PY_INDEX[128] = 16983;
        PINYIN[129] = "jiao";
        PY_INDEX[129] = 16970;
        PINYIN[130] = "jie";
        PY_INDEX[130] = 16942;
        PINYIN[131] = "jin";
        PY_INDEX[131] = 16915;
        PINYIN[132] = "jing";
        PY_INDEX[132] = 16733;
        PINYIN[133] = "jiong";
        PY_INDEX[133] = 16708;
        PINYIN[134] = "jiu";
        PY_INDEX[134] = 16706;
        PINYIN[135] = "ju";
        PY_INDEX[135] = 16689;
        PINYIN[136] = "juan";
        PY_INDEX[136] = 16664;
        PINYIN[137] = "jue";
        PY_INDEX[137] = 16657;
        PINYIN[138] = "jun";
        PY_INDEX[138] = 16647;
        PINYIN[139] = "ka";
        PY_INDEX[139] = 16474;
        PINYIN[140] = "kai";
        PY_INDEX[140] = 16470;
        PINYIN[141] = "kan";
        PY_INDEX[141] = 16465;
        PINYIN[142] = "kang";
        PY_INDEX[142] = 16459;
        PINYIN[143] = "kao";
        PY_INDEX[143] = 16452;
        PINYIN[144] = "ke";
        PY_INDEX[144] = 16448;
        PINYIN[145] = "ken";
        PY_INDEX[145] = 16433;
        PINYIN[146] = "keng";
        PY_INDEX[146] = 16429;
        PINYIN[147] = "kong";
        PY_INDEX[147] = 16427;
        PINYIN[148] = "kou";
        PY_INDEX[148] = 16423;
        PINYIN[149] = "ku";
        PY_INDEX[149] = 16419;
        PINYIN[150] = "kua";
        PY_INDEX[150] = 16412;
        PINYIN[151] = "kuai";
        PY_INDEX[151] = 16407;
        PINYIN[152] = "kuan";
        PY_INDEX[152] = 16403;
        PINYIN[153] = "kuang";
        PY_INDEX[153] = 16401;
        PINYIN[154] = "kui";
        PY_INDEX[154] = 16393;
        PINYIN[155] = "kun";
        PY_INDEX[155] = 16220;
        PINYIN[156] = "kuo";
        PY_INDEX[156] = 16216;
        PINYIN[157] = "la";
        PY_INDEX[157] = 16212;
        PINYIN[158] = "lai";
        PY_INDEX[158] = 16205;
        PINYIN[159] = "lan";
        PY_INDEX[159] = 16202;
        PINYIN[160] = "lang";
        PY_INDEX[160] = 16187;
        PINYIN[161] = "lao";
        PY_INDEX[161] = 16180;
        PINYIN[162] = "le";
        PY_INDEX[162] = 16171;
        PINYIN[163] = "lei";
        PY_INDEX[163] = 16169;
        PINYIN[164] = "leng";
        PY_INDEX[164] = 16158;
        PINYIN[165] = "li";
        PY_INDEX[165] = 16155;
        PINYIN[166] = "lia";
        PY_INDEX[166] = 15959;
        PINYIN[167] = "lian";
        PY_INDEX[167] = 15958;
        PINYIN[168] = "liang";
        PY_INDEX[168] = 15944;
        PINYIN[169] = "liao";
        PY_INDEX[169] = 15933;
        PINYIN[170] = "lie";
        PY_INDEX[170] = 15920;
        PINYIN[171] = "lin";
        PY_INDEX[171] = 15915;
        PINYIN[172] = "ling";
        PY_INDEX[172] = 15903;
        PINYIN[173] = "liu";
        PY_INDEX[173] = 15889;
        PINYIN[174] = "long";
        PY_INDEX[174] = 15878;
        PINYIN[175] = "lou";
        PY_INDEX[175] = 15707;
        PINYIN[176] = "lu";
        PY_INDEX[176] = 15701;
        PINYIN[177] = "lv";
        PY_INDEX[177] = 15681;
        PINYIN[178] = "luan";
        PY_INDEX[178] = 15667;
        PINYIN[179] = "lue";
        PY_INDEX[179] = 15661;
        PINYIN[180] = "lun";
        PY_INDEX[180] = 15659;
        PINYIN[181] = "luo";
        PY_INDEX[181] = 15652;
        PINYIN[182] = "ma";
        PY_INDEX[182] = 15640;
        PINYIN[183] = "mai";
        PY_INDEX[183] = 15631;
        PINYIN[184] = "man";
        PY_INDEX[184] = 15625;
        PINYIN[185] = "mang";
        PY_INDEX[185] = 15454;
        PINYIN[186] = "mao";
        PY_INDEX[186] = 15448;
        PINYIN[187] = "me";
        PY_INDEX[187] = 15436;
        PINYIN[188] = "mei";
        PY_INDEX[188] = 15435;
        PINYIN[189] = "men";
        PY_INDEX[189] = 15419;
        PINYIN[190] = "meng";
        PY_INDEX[190] = 15416;
        PINYIN[191] = "mi";
        PY_INDEX[191] = 15408;
        PINYIN[192] = "mian";
        PY_INDEX[192] = 15394;
        PINYIN[193] = "miao";
        PY_INDEX[193] = 15385;
        PINYIN[194] = "mie";
        PY_INDEX[194] = 15377;
        PINYIN[195] = "min";
        PY_INDEX[195] = 15375;
        PINYIN[196] = "ming";
        PY_INDEX[196] = 15369;
        PINYIN[197] = "miu";
        PY_INDEX[197] = 15363;
        PINYIN[198] = "mo";
        PY_INDEX[198] = 15362;
        PINYIN[199] = "mou";
        PY_INDEX[199] = 15183;
        PINYIN[200] = "mu";
        PY_INDEX[200] = 15180;
        PINYIN[201] = "na";
        PY_INDEX[201] = 15165;
        PINYIN[202] = "nai";
        PY_INDEX[202] = 15158;
        PINYIN[203] = "nan";
        PY_INDEX[203] = 15153;
        PINYIN[204] = "nang";
        PY_INDEX[204] = 15150;
        PINYIN[205] = "nao";
        PY_INDEX[205] = 15149;
        PINYIN[206] = "ne";
        PY_INDEX[206] = 15144;
        PINYIN[207] = "nei";
        PY_INDEX[207] = 15143;
        PINYIN[208] = "nen";
        PY_INDEX[208] = 15141;
        PINYIN[209] = "neng";
        PY_INDEX[209] = 15140;
        PINYIN[210] = "ni";
        PY_INDEX[210] = 15139;
        PINYIN[211] = "nian";
        PY_INDEX[211] = 15128;
        PINYIN[212] = "niang";
        PY_INDEX[212] = 15121;
        PINYIN[213] = "niao";
        PY_INDEX[213] = 15119;
        PINYIN[214] = "nie";
        PY_INDEX[214] = 15117;
        PINYIN[215] = "nin";
        PY_INDEX[215] = 15110;
        PINYIN[216] = "ning";
        PY_INDEX[216] = 15109;
        PINYIN[217] = "niu";
        PY_INDEX[217] = 14941;
        PINYIN[218] = "nong";
        PY_INDEX[218] = 14937;
        PINYIN[219] = "nu";
        PY_INDEX[219] = 14933;
        PINYIN[220] = "nv";
        PY_INDEX[220] = 14930;
        PINYIN[221] = "nuan";
        PY_INDEX[221] = 14929;
        PINYIN[222] = "nue";
        PY_INDEX[222] = 14928;
        PINYIN[223] = "nuo";
        PY_INDEX[223] = 14926;
        PINYIN[224] = "o";
        PY_INDEX[224] = 14922;
        PINYIN[225] = "ou";
        PY_INDEX[225] = 14921;
        PINYIN[226] = "pa";
        PY_INDEX[226] = 14914;
        PINYIN[227] = "pai";
        PY_INDEX[227] = 14908;
        PINYIN[228] = "pan";
        PY_INDEX[228] = 14902;
        PINYIN[229] = "pang";
        PY_INDEX[229] = 14894;
        PINYIN[230] = "pao";
        PY_INDEX[230] = 14889;
        PINYIN[231] = "pei";
        PY_INDEX[231] = 14882;
        PINYIN[232] = "pen";
        PY_INDEX[232] = 14873;
        PINYIN[233] = "peng";
        PY_INDEX[233] = 14871;
        PINYIN[234] = "pi";
        PY_INDEX[234] = 14857;
        PINYIN[235] = "pian";
        PY_INDEX[235] = 14678;
        PINYIN[236] = "piao";
        PY_INDEX[236] = 14674;
        PINYIN[237] = "pie";
        PY_INDEX[237] = 14670;
        PINYIN[238] = "pin";
        PY_INDEX[238] = 14668;
        PINYIN[239] = "ping";
        PY_INDEX[239] = 14663;
        PINYIN[240] = "po";
        PY_INDEX[240] = 14654;
        PINYIN[241] = "pu";
        PY_INDEX[241] = 14645;
        PINYIN[242] = "qi";
        PY_INDEX[242] = 14630;
        PINYIN[243] = "qia";
        PY_INDEX[243] = 14594;
        PINYIN[244] = "qian";
        PY_INDEX[244] = 14429;
        PINYIN[245] = "qiang";
        PY_INDEX[245] = 14407;
        PINYIN[246] = "qiao";
        PY_INDEX[246] = 14399;
        PINYIN[247] = "qie";
        PY_INDEX[247] = 14384;
        PINYIN[248] = "qin";
        PY_INDEX[248] = 14379;
        PINYIN[249] = "qing";
        PY_INDEX[249] = 14368;
        PINYIN[250] = "qiong";
        PY_INDEX[250] = 14355;
        PINYIN[251] = "qiu";
        PY_INDEX[251] = 14353;
        PINYIN[252] = "qu";
        PY_INDEX[252] = 14345;
        PINYIN[253] = "quan";
        PY_INDEX[253] = 14170;
        PINYIN[254] = "que";
        PY_INDEX[254] = 14159;
        PINYIN[255] = "qun";
        PY_INDEX[255] = 14151;
        PINYIN[256] = "ran";
        PY_INDEX[256] = 14149;
        PINYIN[257] = "rang";
        PY_INDEX[257] = 14145;
        PINYIN[258] = "rao";
        PY_INDEX[258] = 14140;
        PINYIN[259] = "re";
        PY_INDEX[259] = 14137;
        PINYIN[260] = "ren";
        PY_INDEX[260] = 14135;
        PINYIN[261] = "reng";
        PY_INDEX[261] = 14125;
        PINYIN[262] = "ri";
        PY_INDEX[262] = 14123;
        PINYIN[263] = "rong";
        PY_INDEX[263] = 14122;
        PINYIN[264] = "rou";
        PY_INDEX[264] = 14112;
        PINYIN[265] = "ru";
        PY_INDEX[265] = 14109;
        PINYIN[266] = "ruan";
        PY_INDEX[266] = 14099;
        PINYIN[267] = "rui";
        PY_INDEX[267] = 14097;
        PINYIN[268] = "run";
        PY_INDEX[268] = 14094;
        PINYIN[269] = "ruo";
        PY_INDEX[269] = 14092;
        PINYIN[270] = "sa";
        PY_INDEX[270] = 14090;
        PINYIN[271] = "sai";
        PY_INDEX[271] = 14087;
        PINYIN[272] = "san";
        PY_INDEX[272] = 14083;
        PINYIN[273] = "sang";
        PY_INDEX[273] = 13917;
        PINYIN[274] = "sao";
        PY_INDEX[274] = 13914;
        PINYIN[275] = "se";
        PY_INDEX[275] = 13910;
        PINYIN[276] = "sen";
        PY_INDEX[276] = 13907;
        PINYIN[277] = "seng";
        PY_INDEX[277] = 13906;
        PINYIN[278] = "sha";
        PY_INDEX[278] = 13905;
        PINYIN[279] = "shai";
        PY_INDEX[279] = 13896;
        PINYIN[280] = "shan";
        PY_INDEX[280] = 13894;
        PINYIN[281] = "shang";
        PY_INDEX[281] = 13878;
        PINYIN[282] = "shao";
        PY_INDEX[282] = 13870;
        PINYIN[283] = "she";
        PY_INDEX[283] = 13859;
        PINYIN[284] = "shen";
        PY_INDEX[284] = 13847;
        PINYIN[285] = "sheng";
        PY_INDEX[285] = 13831;
        PINYIN[286] = "shi";
        PY_INDEX[286] = 13658;
        PINYIN[287] = "shou";
        PY_INDEX[287] = 13611;
        PINYIN[288] = "shu";
        PY_INDEX[288] = 13601;
        PINYIN[289] = "shua";
        PY_INDEX[289] = 13406;
        PINYIN[290] = "shuai";
        PY_INDEX[290] = 13404;
        PINYIN[291] = "shuan";
        PY_INDEX[291] = 13400;
        PINYIN[292] = "shuang";
        PY_INDEX[292] = 13398;
        PINYIN[293] = "shui";
        PY_INDEX[293] = 13395;
        PINYIN[294] = "shun";
        PY_INDEX[294] = 13391;
        PINYIN[295] = "shuo";
        PY_INDEX[295] = 13387;
        PINYIN[296] = "si";
        PY_INDEX[296] = 13383;
        PINYIN[297] = "song";
        PY_INDEX[297] = 13367;
        PINYIN[298] = "sou";
        PY_INDEX[298] = 13359;
        PINYIN[299] = "su";
        PY_INDEX[299] = 13356;
        PINYIN[300] = "suan";
        PY_INDEX[300] = 13343;
        PINYIN[301] = "sui";
        PY_INDEX[301] = 13340;
        PINYIN[302] = "sun";
        PY_INDEX[302] = 13329;
        PINYIN[303] = "suo";
        PY_INDEX[303] = 13326;
        PINYIN[304] = "ta";
        PY_INDEX[304] = 13318;
        PINYIN[305] = "tai";
        PY_INDEX[305] = 13147;
        PINYIN[306] = "tan";
        PY_INDEX[306] = 13138;
        PINYIN[307] = "tang";
        PY_INDEX[307] = 13120;
        PINYIN[308] = "tao";
        PY_INDEX[308] = 13107;
        PINYIN[309] = "te";
        PY_INDEX[309] = 13096;
        PINYIN[310] = "teng";
        PY_INDEX[310] = 13095;
        PINYIN[311] = "ti";
        PY_INDEX[311] = 13091;
        PINYIN[312] = "tian";
        PY_INDEX[312] = 13076;
        PINYIN[313] = "tiao";
        PY_INDEX[313] = 13068;
        PINYIN[314] = "tie";
        PY_INDEX[314] = 13063;
        PINYIN[315] = "ting";
        PY_INDEX[315] = 13060;
        PINYIN[316] = "tong";
        PY_INDEX[316] = 12888;
        PINYIN[317] = "tou";
        PY_INDEX[317] = 12875;
        PINYIN[318] = "tu";
        PY_INDEX[318] = 12871;
        PINYIN[319] = "tuan";
        PY_INDEX[319] = 12860;
        PINYIN[320] = "tui";
        PY_INDEX[320] = 12858;
        PINYIN[321] = "tun";
        PY_INDEX[321] = 12852;
        PINYIN[322] = "tuo";
        PY_INDEX[322] = 12849;
        PINYIN[323] = "wa";
        PY_INDEX[323] = 12838;
        PINYIN[324] = "wai";
        PY_INDEX[324] = 12831;
        PINYIN[325] = "wan";
        PY_INDEX[325] = 12829;
        PINYIN[326] = "wang";
        PY_INDEX[326] = 12812;
        PINYIN[327] = "wei";
        PY_INDEX[327] = 12802;
        PINYIN[328] = "wen";
        PY_INDEX[328] = 12607;
        PINYIN[329] = "weng";
        PY_INDEX[329] = 12597;
        PINYIN[330] = "wo";
        PY_INDEX[330] = 12594;
        PINYIN[331] = "wu";
        PY_INDEX[331] = 12585;
        PINYIN[332] = "xi";
        PY_INDEX[332] = 12556;
        PINYIN[333] = "xia";
        PY_INDEX[333] = 12359;
        PINYIN[334] = "xian";
        PY_INDEX[334] = 12346;
        PINYIN[335] = "xiang";
        PY_INDEX[335] = 12320;
        PINYIN[336] = "xiao";
        PY_INDEX[336] = 12300;
        PINYIN[337] = "xie";
        PY_INDEX[337] = 12120;
        PINYIN[338] = "xin";
        PY_INDEX[338] = 12099;
        PINYIN[339] = "xing";
        PY_INDEX[339] = 12089;
        PINYIN[340] = "xiong";
        PY_INDEX[340] = 12074;
        PINYIN[341] = "xiu";
        PY_INDEX[341] = 12067;
        PINYIN[342] = "xu";
        PY_INDEX[342] = 12058;
        PINYIN[343] = "xuan";
        PY_INDEX[343] = 12039;
        PINYIN[344] = "xue";
        PY_INDEX[344] = 11867;
        PINYIN[345] = "xun";
        PY_INDEX[345] = 11861;
        PINYIN[346] = "ya";
        PY_INDEX[346] = 11847;
        PINYIN[347] = "yan";
        PY_INDEX[347] = 11831;
        PINYIN[348] = "yang";
        PY_INDEX[348] = 11798;
        PINYIN[349] = "yao";
        PY_INDEX[349] = 11781;
        PINYIN[350] = "ye";
        PY_INDEX[350] = 11604;
        PINYIN[351] = "yi";
        PY_INDEX[351] = 11589;
        PINYIN[352] = "yin";
        PY_INDEX[352] = 11536;
        PINYIN[353] = "ying";
        PY_INDEX[353] = 11358;
        PINYIN[354] = "yo";
        PY_INDEX[354] = 11340;
        PINYIN[355] = "yong";
        PY_INDEX[355] = 11339;
        PINYIN[356] = "you";
        PY_INDEX[356] = 11324;
        PINYIN[357] = "yu";
        PY_INDEX[357] = 11303;
        PINYIN[358] = "yuan";
        PY_INDEX[358] = 11097;
        PINYIN[359] = "yue";
        PY_INDEX[359] = 11077;
        PINYIN[360] = "yun";
        PY_INDEX[360] = 11067;
        PINYIN[361] = "za";
        PY_INDEX[361] = 11055;
        PINYIN[362] = "zai";
        PY_INDEX[362] = 11052;
        PINYIN[363] = "zan";
        PY_INDEX[363] = 11045;
        PINYIN[364] = "zang";
        PY_INDEX[364] = 11041;
        PINYIN[365] = "zao";
        PY_INDEX[365] = 11038;
        PINYIN[366] = "ze";
        PY_INDEX[366] = 11024;
        PINYIN[367] = "zei";
        PY_INDEX[367] = 11020;
        PINYIN[368] = "zen";
        PY_INDEX[368] = 11019;
        PINYIN[369] = "zeng";
        PY_INDEX[369] = 11018;
        PINYIN[370] = "zha";
        PY_INDEX[370] = 11014;
        PINYIN[371] = "zhai";
        PY_INDEX[371] = 10838;
        PINYIN[372] = "zhan";
        PY_INDEX[372] = 10832;
        PINYIN[373] = "zhang";
        PY_INDEX[373] = 10815;
        PINYIN[374] = "zhao";
        PY_INDEX[374] = 10800;
        PINYIN[375] = "zhe";
        PY_INDEX[375] = 10790;
        PINYIN[376] = "zhen";
        PY_INDEX[376] = 10780;
        PINYIN[377] = "zheng";
        PY_INDEX[377] = 10764;
        PINYIN[378] = "zhi";
        PY_INDEX[378] = 10587;
        PINYIN[379] = "zhong";
        PY_INDEX[379] = 10544;
        PINYIN[380] = "zhou";
        PY_INDEX[380] = 10533;
        PINYIN[381] = "zhu";
        PY_INDEX[381] = 10519;
        PINYIN[382] = "zhua";
        PY_INDEX[382] = 10331;
        PINYIN[383] = "zhuai";
        PY_INDEX[383] = 10329;
        PINYIN[384] = "zhuan";
        PY_INDEX[384] = 10328;
        PINYIN[385] = "zhuang";
        PY_INDEX[385] = 10322;
        PINYIN[386] = "zhui";
        PY_INDEX[386] = 10315;
        PINYIN[387] = "zhun";
        PY_INDEX[387] = 10309;
        PINYIN[388] = "zhuo";
        PY_INDEX[388] = 10307;
        PINYIN[389] = "zi";
        PY_INDEX[389] = 10296;
        PINYIN[390] = "zong";
        PY_INDEX[390] = 10281;
        PINYIN[391] = "zou";
        PY_INDEX[391] = 10274;
        PINYIN[392] = "zu";
        PY_INDEX[392] = 10270;
        PINYIN[393] = "zuan";
        PY_INDEX[393] = 10262;
        PINYIN[394] = "zui";
        PY_INDEX[394] = 10260;
        PINYIN[395] = "zun";
        PY_INDEX[395] = 10256;
        PINYIN[396] = "zuo";
        PY_INDEX[396] = 10254;

        for ( int i = 0; i < LENGTH; i++ ) {
            PY_INDEX[i] = -PY_INDEX[i];
        }
    }
}
