package com.lfscheidegger.jfacet.shade.expression.vector.swizzle;

/**
 * The enumerations in this class contain all possible combination of swizzle values for 2-, 3-, and 4-dimensional
 * values. All expressions that support swizzling take one of these enums as parameters, ensuring that the
 * dimensionality of the swizzling requested, as well as the coordinates used, are always valid.
 *
 * For example, in GLSL, one can mistakenly write vec2(1, 2).z, which fails to compile. We avoid this because
 * {@code Vector2} supports only 2-dimensional swizzling, which uses the {@code D21}, {@code D22}, {@code D23},
 * and {@code D24} enumerations.
 *
 * This class has an exceptionally short name (as well as the enums themselves) to deemphasize their names when
 * compared to the value in the enums - the "XXY" part should draw more attention than the class/enum name itself.
 */
public interface Swizzle {

  public static enum D21 implements Swizzle {
    X("x"), Y("y"),
    R("r"), G("g"),
    S("s"), T("t");

    private final String mValue;

    D21(String value) {
      mValue = value;
    }

    @Override
    public String toString() {
      return mValue;
    }
  }

  public static enum D22 implements Swizzle {
    XX("xx"), XY("xy"),
    YX("yx"), YY("yy"),
    RR("rr"), RG("rg"),
    GR("gr"), GG("gg"),
    SS("ss"), ST("st"),
    TS("ts"), TT("tt");

    private final String mValue;

    D22(String value) {
      mValue = value;
    }

    @Override
    public String toString() {
      return mValue;
    }
  }

  public static enum D23 implements Swizzle {
    XXX("xxx"), XXY("xxy"),
    XYX("xyx"), XYY("xyy"),
    YXX("yxx"), YXY("yxy"),
    YYX("yyx"), YYY("yyy"),
    RRR("rrr"), RRG("rrg"),
    RGR("rgr"), RGG("rgg"),
    GRR("grr"), GRG("grg"),
    GGR("ggr"), GGG("ggg"),
    SSS("sss"), SST("sst"),
    STS("sts"), STT("stt"),
    TSS("tss"), TST("tst"),
    TTS("tts"), TTT("ttt");

    private final String mValue;

    D23(String value) {
      mValue = value;
    }

    @Override
    public String toString() {
      return mValue;
    }
  }

  public static enum D24 implements Swizzle {
    XXXX("xxxx"), XXXY("xxxy"),
    XXYX("xxyx"), XXYY("xxyy"),
    XYXX("xyxx"), XYXY("xyxy"),
    XYYX("xyyx"), XYYY("xyyy"),
    YXXX("yxxx"), YXXY("yxxy"),
    YXYX("yxyx"), YXYY("yxyy"),
    YYXX("yyxx"), YYXY("yyxy"),
    YYYX("yyyx"), YYYY("yyyy"),
    RRRR("rrrr"), RRRG("rrrg"),
    RRGR("rrgr"), RRGG("rrgg"),
    RGRR("rgrr"), RGRG("rgrg"),
    RGGR("rggr"), RGGG("rggg"),
    GRRR("grrr"), GRRG("grrg"),
    GRGR("grgr"), GRGG("grgg"),
    GGRR("ggrr"), GGRG("ggrg"),
    GGGR("gggr"), GGGG("gggg"),
    SSSS("ssss"), SSST("ssst"),
    SSTS("ssts"), SSTT("sstt"),
    STSS("stss"), STST("stst"),
    STTS("stts"), STTT("sttt"),
    TSSS("tsss"), TSST("tsst"),
    TSTS("tsts"), TSTT("tstt"),
    TTSS("ttss"), TTST("ttst"),
    TTTS("ttts"), TTTT("tttt");

    private final String mValue;

    D24(String value) {
      mValue = value;
    }

    @Override
    public String toString() {
      return mValue;
    }
  }

  public static enum D31 implements Swizzle {
    X("x"), Y("y"), Z("z"),
    R("r"), G("g"), B("b"),
    S("s"), T("t"), P("p");

    private final String mValue;

    D31(String value) {
      mValue = value;
    }

    @Override
    public String toString() {
      return mValue;
    }
  }

  public static enum D32 implements Swizzle {
    XX("xx"), XY("xy"), XZ("xz"),
    YX("yx"), YY("yy"), YZ("yz"),
    ZX("zx"), ZY("zy"), ZZ("zz"),
    RR("rr"), RG("rg"), RB("rb"),
    GR("gr"), GG("gg"), GB("gb"),
    BR("br"), BG("bg"), BB("bb"),
    SS("ss"), ST("st"), SP("sp"),
    TS("ts"), TT("tt"), TP("tp"),
    PS("ps"), PT("pt"), PP("pp");

    private final String mValue;

    D32(String value) {
      mValue = value;
    }

    @Override
    public String toString() {
      return mValue;
    }
  }

  public static enum D33 implements Swizzle {
    XXX("xxx"), XXY("xxy"), XXZ("xxz"),
    XYX("xyx"), XYY("xyy"), XYZ("xyz"),
    XZX("xzx"), XZY("xzy"), XZZ("xzz"),
    YXX("yxx"), YXY("yxy"), YXZ("yxz"),
    YYX("yyx"), YYY("yyy"), YYZ("yyz"),
    YZX("yzx"), YZY("yzy"), YZZ("yzz"),
    ZXX("zxx"), ZXY("zxy"), ZXZ("zxz"),
    ZYX("zyx"), ZYY("zyy"), ZYZ("zyz"),
    ZZX("zzx"), ZZY("zzy"), ZZZ("zzz"),
    RRR("rrr"), RRG("rrg"), RRB("rrb"),
    RGR("rgr"), RGG("rgg"), RGB("rgb"),
    RBR("rbr"), RBG("rbg"), RBB("rbb"),
    GRR("grr"), GRG("grg"), GRB("grb"),
    GGR("ggr"), GGG("ggg"), GGB("ggb"),
    GBR("gbr"), GBG("gbg"), GBB("gbb"),
    BRR("brr"), BRG("brg"), BRB("brb"),
    BGR("bgr"), BGG("bgg"), BGB("bgb"),
    BBR("bbr"), BBG("bbg"), BBB("bbb"),
    SSS("sss"), SST("sst"), SSP("ssp"),
    STS("sts"), STT("stt"), STP("stp"),
    SPS("sps"), SPT("spt"), SPP("spp"),
    TSS("tss"), TST("tst"), TSP("tsp"),
    TTS("tts"), TTT("ttt"), TTP("ttp"),
    TPS("tps"), TPT("tpt"), TPP("tpp"),
    PSS("pss"), PST("pst"), PSP("psp"),
    PTS("pts"), PTT("ptt"), PTP("ptp"),
    PPS("pps"), PPT("ppt"), PPP("ppp");

    private final String mValue;

    D33(String value) {
      mValue = value;
    }

    @Override
    public String toString() {
      return mValue;
    }
  }

  public static enum D34 implements Swizzle {
    XXXX("xxxx"), XXXY("xxxy"), XXXZ("xxxz"),
    XXYX("xxyx"), XXYY("xxyy"), XXYZ("xxyz"),
    XXZX("xxzx"), XXZY("xxzy"), XXZZ("xxzz"),
    XYXX("xyxx"), XYXY("xyxy"), XYXZ("xyxz"),
    XYYX("xyyx"), XYYY("xyyy"), XYYZ("xyyz"),
    XYZX("xyzx"), XYZY("xyzy"), XYZZ("xyzz"),
    XZXX("xzxx"), XZXY("xzxy"), XZXZ("xzxz"),
    XZYX("xzyx"), XZYY("xzyy"), XZYZ("xzyz"),
    XZZX("xzzx"), XZZY("xzzy"), XZZZ("xzzz"),
    YXXX("yxxx"), YXXY("yxxy"), YXXZ("yxxz"),
    YXYX("yxyx"), YXYY("yxyy"), YXYZ("yxyz"),
    YXZX("yxzx"), YXZY("yxzy"), YXZZ("yxzz"),
    YYXX("yyxx"), YYXY("yyxy"), YYXZ("yyxz"),
    YYYX("yyyx"), YYYY("yyyy"), YYYZ("yyyz"),
    YYZX("yyzx"), YYZY("yyzy"), YYZZ("yyzz"),
    YZXX("yzxx"), YZXY("yzxy"), YZXZ("yzxz"),
    YZYX("yzyx"), YZYY("yzyy"), YZYZ("yzyz"),
    YZZX("yzzx"), YZZY("yzzy"), YZZZ("yzzz"),
    ZXXX("zxxx"), ZXXY("zxxy"), ZXXZ("zxxz"),
    ZXYX("zxyx"), ZXYY("zxyy"), ZXYZ("zxyz"),
    ZXZX("zxzx"), ZXZY("zxzy"), ZXZZ("zxzz"),
    ZYXX("zyxx"), ZYXY("zyxy"), ZYXZ("zyxz"),
    ZYYX("zyyx"), ZYYY("zyyy"), ZYYZ("zyyz"),
    ZYZX("zyzx"), ZYZY("zyzy"), ZYZZ("zyzz"),
    ZZXX("zzxx"), ZZXY("zzxy"), ZZXZ("zzxz"),
    ZZYX("zzyx"), ZZYY("zzyy"), ZZYZ("zzyz"),
    ZZZX("zzzx"), ZZZY("zzzy"), ZZZZ("zzzz"),
    RRRR("rrrr"), RRRG("rrrg"), RRRB("rrrb"),
    RRGR("rrgr"), RRGG("rrgg"), RRGB("rrgb"),
    RRBR("rrbr"), RRBG("rrbg"), RRBB("rrbb"),
    RGRR("rgrr"), RGRG("rgrg"), RGRB("rgrb"),
    RGGR("rggr"), RGGG("rggg"), RGGB("rggb"),
    RGBR("rgbr"), RGBG("rgbg"), RGBB("rgbb"),
    RBRR("rbrr"), RBRG("rbrg"), RBRB("rbrb"),
    RBGR("rbgr"), RBGG("rbgg"), RBGB("rbgb"),
    RBBR("rbbr"), RBBG("rbbg"), RBBB("rbbb"),
    GRRR("grrr"), GRRG("grrg"), GRRB("grrb"),
    GRGR("grgr"), GRGG("grgg"), GRGB("grgb"),
    GRBR("grbr"), GRBG("grbg"), GRBB("grbb"),
    GGRR("ggrr"), GGRG("ggrg"), GGRB("ggrb"),
    GGGR("gggr"), GGGG("gggg"), GGGB("gggb"),
    GGBR("ggbr"), GGBG("ggbg"), GGBB("ggbb"),
    GBRR("gbrr"), GBRG("gbrg"), GBRB("gbrb"),
    GBGR("gbgr"), GBGG("gbgg"), GBGB("gbgb"),
    GBBR("gbbr"), GBBG("gbbg"), GBBB("gbbb"),
    BRRR("brrr"), BRRG("brrg"), BRRB("brrb"),
    BRGR("brgr"), BRGG("brgg"), BRGB("brgb"),
    BRBR("brbr"), BRBG("brbg"), BRBB("brbb"),
    BGRR("bgrr"), BGRG("bgrg"), BGRB("bgrb"),
    BGGR("bggr"), BGGG("bggg"), BGGB("bggb"),
    BGBR("bgbr"), BGBG("bgbg"), BGBB("bgbb"),
    BBRR("bbrr"), BBRG("bbrg"), BBRB("bbrb"),
    BBGR("bbgr"), BBGG("bbgg"), BBGB("bbgb"),
    BBBR("bbbr"), BBBG("bbbg"), BBBB("bbbb"),
    SSSS("ssss"), SSST("ssst"), SSSP("sssp"),
    SSTS("ssts"), SSTT("sstt"), SSTP("sstp"),
    SSPS("ssps"), SSPT("sspt"), SSPP("sspp"),
    STSS("stss"), STST("stst"), STSP("stsp"),
    STTS("stts"), STTT("sttt"), STTP("sttp"),
    STPS("stps"), STPT("stpt"), STPP("stpp"),
    SPSS("spss"), SPST("spst"), SPSP("spsp"),
    SPTS("spts"), SPTT("sptt"), SPTP("sptp"),
    SPPS("spps"), SPPT("sppt"), SPPP("sppp"),
    TSSS("tsss"), TSST("tsst"), TSSP("tssp"),
    TSTS("tsts"), TSTT("tstt"), TSTP("tstp"),
    TSPS("tsps"), TSPT("tspt"), TSPP("tspp"),
    TTSS("ttss"), TTST("ttst"), TTSP("ttsp"),
    TTTS("ttts"), TTTT("tttt"), TTTP("tttp"),
    TTPS("ttps"), TTPT("ttpt"), TTPP("ttpp"),
    TPSS("tpss"), TPST("tpst"), TPSP("tpsp"),
    TPTS("tpts"), TPTT("tptt"), TPTP("tptp"),
    TPPS("tpps"), TPPT("tppt"), TPPP("tppp"),
    PSSS("psss"), PSST("psst"), PSSP("pssp"),
    PSTS("psts"), PSTT("pstt"), PSTP("pstp"),
    PSPS("psps"), PSPT("pspt"), PSPP("pspp"),
    PTSS("ptss"), PTST("ptst"), PTSP("ptsp"),
    PTTS("ptts"), PTTT("pttt"), PTTP("pttp"),
    PTPS("ptps"), PTPT("ptpt"), PTPP("ptpp"),
    PPSS("ppss"), PPST("ppst"), PPSP("ppsp"),
    PPTS("ppts"), PPTT("pptt"), PPTP("pptp");

    private final String mValue;

    D34(String value) {
      mValue = value;
    }

    @Override
    public String toString() {
      return mValue;
    }
  }

  public static enum D41 implements Swizzle {
    X("x"), Y("y"), Z("z"), W("w"),
    R("r"), G("g"), B("b"), A("a"),
    S("s"), T("t"), P("p"), Q("q");

    private final String mValue;

    D41(String value) {
      mValue = value;
    }

    @Override
    public String toString() {
      return mValue;
    }
  }

  public static enum D42 implements Swizzle {
    XX("xx"), XY("xy"), XZ("xz"), XW("xw"),
    YX("yx"), YY("yy"), YZ("yz"), YW("yw"),
    ZX("zx"), ZY("zy"), ZZ("zz"), ZW("zw"),
    WX("wx"), WY("wy"), WZ("wz"), WW("ww"),
    RR("rr"), RG("rg"), RB("rb"), RA("ra"),
    GR("gr"), GG("gg"), GB("gb"), GA("ga"),
    BR("br"), BG("bg"), BB("bb"), BA("ba"),
    AR("ar"), AG("ag"), AB("ab"), AA("aa"),
    SS("ss"), ST("st"), SP("sp"), SQ("sq"),
    TS("ts"), TT("tt"), TP("tp"), TQ("tq"),
    PS("ps"), PT("pt"), PP("pp"), PQ("pq"),
    QS("qs"), QT("qt"), QP("qp"), QQ("qq");

    private final String mValue;

    D42(String value) {
      mValue = value;
    }

    @Override
    public String toString() {
      return mValue;
    }
  }

  public static enum D43 implements Swizzle {
    XXX("xxx"), XXY("xxy"), XXZ("xxz"), XXW("xxw"),
    XYX("xyx"), XYY("xyy"), XYZ("xyz"), XYW("xyw"),
    XZX("xzx"), XZY("xzy"), XZZ("xzz"), XZW("xzw"),
    XWX("xwx"), XWY("xwy"), XWZ("xwz"), XWW("xww"),
    YXX("yxx"), YXY("yxy"), YXZ("yxz"), YXW("yxw"),
    YYX("yyx"), YYY("yyy"), YYZ("yyz"), YYW("yyw"),
    YZX("yzx"), YZY("yzy"), YZZ("yzz"), YZW("yzw"),
    YWX("ywx"), YWY("ywy"), YWZ("ywz"), YWW("yww"),
    ZXX("zxx"), ZXY("zxy"), ZXZ("zxz"), ZXW("zxw"),
    ZYX("zyx"), ZYY("zyy"), ZYZ("zyz"), ZYW("zyw"),
    ZZX("zzx"), ZZY("zzy"), ZZZ("zzz"), ZZW("zzw"),
    ZWX("zwx"), ZWY("zwy"), ZWZ("zwz"), ZWW("zww"),
    WXX("wxx"), WXY("wxy"), WXZ("wxz"), WXW("wxw"),
    WYX("wyx"), WYY("wyy"), WYZ("wyz"), WYW("wyw"),
    WZX("wzx"), WZY("wzy"), WZZ("wzz"), WZW("wzw"),
    WWX("wwx"), WWY("wwy"), WWZ("wwz"), WWW("www"),
    RRR("rrr"), RRG("rrg"), RRB("rrb"), RRA("rra"),
    RGR("rgr"), RGG("rgg"), RGB("rgb"), RGA("rga"),
    RBR("rbr"), RBG("rbg"), RBB("rbb"), RBA("rba"),
    RAR("rar"), RAG("rag"), RAB("rab"), RAA("raa"),
    GRR("grr"), GRG("grg"), GRB("grb"), GRA("gra"),
    GGR("ggr"), GGG("ggg"), GGB("ggb"), GGA("gga"),
    GBR("gbr"), GBG("gbg"), GBB("gbb"), GBA("gba"),
    GAR("gar"), GAG("gag"), GAB("gab"), GAA("gaa"),
    BRR("brr"), BRG("brg"), BRB("brb"), BRA("bra"),
    BGR("bgr"), BGG("bgg"), BGB("bgb"), BGA("bga"),
    BBR("bbr"), BBG("bbg"), BBB("bbb"), BBA("bba"),
    BAR("bar"), BAG("bag"), BAB("bab"), BAA("baa"),
    ARR("arr"), ARG("arg"), ARB("arb"), ARA("ara"),
    AGR("agr"), AGG("agg"), AGB("agb"), AGA("aga"),
    ABR("abr"), ABG("abg"), ABB("abb"), ABA("aba"),
    AAR("aar"), AAG("aag"), AAB("aab"), AAA("aaa"),
    SSS("sss"), SST("sst"), SSP("ssp"), SSQ("ssq"),
    STS("sts"), STT("stt"), STP("stp"), STQ("stq"),
    SPS("sps"), SPT("spt"), SPP("spp"), SPQ("spq"),
    SQS("sqs"), SQT("sqt"), SQP("sqp"), SQQ("sqq"),
    TSS("tss"), TST("tst"), TSP("tsp"), TSQ("tsq"),
    TTS("tts"), TTT("ttt"), TTP("ttp"), TTQ("ttq"),
    TPS("tps"), TPT("tpt"), TPP("tpp"), TPQ("tpq"),
    TQS("tqs"), TQT("tqt"), TQP("tqp"), TQQ("tqq"),
    PSS("pss"), PST("pst"), PSP("psp"), PSQ("psq"),
    PTS("pts"), PTT("ptt"), PTP("ptp"), PTQ("ptq"),
    PPS("pps"), PPT("ppt"), PPP("ppp"), PPQ("ppq"),
    PQS("pqs"), PQT("pqt"), PQP("pqp"), PQQ("pqq"),
    QSS("qss"), QST("qst"), QSP("qsp"), QSQ("qsq"),
    QTS("qts"), QTT("qtt"), QTP("qtp"), QTQ("qtq"),
    QPS("qps"), QPT("qpt"), QPP("qpp"), QPQ("qpq"),
    QQS("qqs"), QQT("qqt"), QQP("qqp"), QQQ("qqq");

    private final String mValue;

    D43(String value) {
      mValue = value;
    }

    @Override
    public String toString() {
      return mValue;
    }
  }

  public static enum D44 implements Swizzle {
    XXXX("xxxx"), XXXY("xxxy"), XXXZ("xxxz"), XXXW("xxxw"),
    XXYX("xxyx"), XXYY("xxyy"), XXYZ("xxyz"), XXYW("xxyw"),
    XXZX("xxzx"), XXZY("xxzy"), XXZZ("xxzz"), XXZW("xxzw"),
    XXWX("xxwx"), XXWY("xxwy"), XXWZ("xxwz"), XXWW("xxww"),
    XYXX("xyxx"), XYXY("xyxy"), XYXZ("xyxz"), XYXW("xyxw"),
    XYYX("xyyx"), XYYY("xyyy"), XYYZ("xyyz"), XYYW("xyyw"),
    XYZX("xyzx"), XYZY("xyzy"), XYZZ("xyzz"), XYZW("xyzw"),
    XYWX("xywx"), XYWY("xywy"), XYWZ("xywz"), XYWW("xyww"),
    XZXX("xzxx"), XZXY("xzxy"), XZXZ("xzxz"), XZXW("xzxw"),
    XZYX("xzyx"), XZYY("xzyy"), XZYZ("xzyz"), XZYW("xzyw"),
    XZZX("xzzx"), XZZY("xzzy"), XZZZ("xzzz"), XZZW("xzzw"),
    XZWX("xzwx"), XZWY("xzwy"), XZWZ("xzwz"), XZWW("xzww"),
    XWXX("xwxx"), XWXY("xwxy"), XWXZ("xwxz"), XWXW("xwxw"),
    XWYX("xwyx"), XWYY("xwyy"), XWYZ("xwyz"), XWYW("xwyw"),
    XWZX("xwzx"), XWZY("xwzy"), XWZZ("xwzz"), XWZW("xwzw"),
    XWWX("xwwx"), XWWY("xwwy"), XWWZ("xwwz"), XWWW("xwww"),
    YXXX("yxxx"), YXXY("yxxy"), YXXZ("yxxz"), YXXW("yxxw"),
    YXYX("yxyx"), YXYY("yxyy"), YXYZ("yxyz"), YXYW("yxyw"),
    YXZX("yxzx"), YXZY("yxzy"), YXZZ("yxzz"), YXZW("yxzw"),
    YXWX("yxwx"), YXWY("yxwy"), YXWZ("yxwz"), YXWW("yxww"),
    YYXX("yyxx"), YYXY("yyxy"), YYXZ("yyxz"), YYXW("yyxw"),
    YYYX("yyyx"), YYYY("yyyy"), YYYZ("yyyz"), YYYW("yyyw"),
    YYZX("yyzx"), YYZY("yyzy"), YYZZ("yyzz"), YYZW("yyzw"),
    YYWX("yywx"), YYWY("yywy"), YYWZ("yywz"), YYWW("yyww"),
    YZXX("yzxx"), YZXY("yzxy"), YZXZ("yzxz"), YZXW("yzxw"),
    YZYX("yzyx"), YZYY("yzyy"), YZYZ("yzyz"), YZYW("yzyw"),
    YZZX("yzzx"), YZZY("yzzy"), YZZZ("yzzz"), YZZW("yzzw"),
    YZWX("yzwx"), YZWY("yzwy"), YZWZ("yzwz"), YZWW("yzww"),
    YWXX("ywxx"), YWXY("ywxy"), YWXZ("ywxz"), YWXW("ywxw"),
    YWYX("ywyx"), YWYY("ywyy"), YWYZ("ywyz"), YWYW("ywyw"),
    YWZX("ywzx"), YWZY("ywzy"), YWZZ("ywzz"), YWZW("ywzw"),
    YWWX("ywwx"), YWWY("ywwy"), YWWZ("ywwz"), YWWW("ywww"),
    ZXXX("zxxx"), ZXXY("zxxy"), ZXXZ("zxxz"), ZXXW("zxxw"),
    ZXYX("zxyx"), ZXYY("zxyy"), ZXYZ("zxyz"), ZXYW("zxyw"),
    ZXZX("zxzx"), ZXZY("zxzy"), ZXZZ("zxzz"), ZXZW("zxzw"),
    ZXWX("zxwx"), ZXWY("zxwy"), ZXWZ("zxwz"), ZXWW("zxww"),
    ZYXX("zyxx"), ZYXY("zyxy"), ZYXZ("zyxz"), ZYXW("zyxw"),
    ZYYX("zyyx"), ZYYY("zyyy"), ZYYZ("zyyz"), ZYYW("zyyw"),
    ZYZX("zyzx"), ZYZY("zyzy"), ZYZZ("zyzz"), ZYZW("zyzw"),
    ZYWX("zywx"), ZYWY("zywy"), ZYWZ("zywz"), ZYWW("zyww"),
    ZZXX("zzxx"), ZZXY("zzxy"), ZZXZ("zzxz"), ZZXW("zzxw"),
    ZZYX("zzyx"), ZZYY("zzyy"), ZZYZ("zzyz"), ZZYW("zzyw"),
    ZZZX("zzzx"), ZZZY("zzzy"), ZZZZ("zzzz"), ZZZW("zzzw"),
    ZZWX("zzwx"), ZZWY("zzwy"), ZZWZ("zzwz"), ZZWW("zzww"),
    ZWXX("zwxx"), ZWXY("zwxy"), ZWXZ("zwxz"), ZWXW("zwxw"),
    ZWYX("zwyx"), ZWYY("zwyy"), ZWYZ("zwyz"), ZWYW("zwyw"),
    ZWZX("zwzx"), ZWZY("zwzy"), ZWZZ("zwzz"), ZWZW("zwzw"),
    ZWWX("zwwx"), ZWWY("zwwy"), ZWWZ("zwwz"), ZWWW("zwww"),
    WXXX("wxxx"), WXXY("wxxy"), WXXZ("wxxz"), WXXW("wxxw"),
    WXYX("wxyx"), WXYY("wxyy"), WXYZ("wxyz"), WXYW("wxyw"),
    WXZX("wxzx"), WXZY("wxzy"), WXZZ("wxzz"), WXZW("wxzw"),
    WXWX("wxwx"), WXWY("wxwy"), WXWZ("wxwz"), WXWW("wxww"),
    WYXX("wyxx"), WYXY("wyxy"), WYXZ("wyxz"), WYXW("wyxw"),
    WYYX("wyyx"), WYYY("wyyy"), WYYZ("wyyz"), WYYW("wyyw"),
    WYZX("wyzx"), WYZY("wyzy"), WYZZ("wyzz"), WYZW("wyzw"),
    WYWX("wywx"), WYWY("wywy"), WYWZ("wywz"), WYWW("wyww"),
    WZXX("wzxx"), WZXY("wzxy"), WZXZ("wzxz"), WZXW("wzxw"),
    WZYX("wzyx"), WZYY("wzyy"), WZYZ("wzyz"), WZYW("wzyw"),
    WZZX("wzzx"), WZZY("wzzy"), WZZZ("wzzz"), WZZW("wzzw"),
    WZWX("wzwx"), WZWY("wzwy"), WZWZ("wzwz"), WZWW("wzww"),
    WWXX("wwxx"), WWXY("wwxy"), WWXZ("wwxz"), WWXW("wwxw"),
    WWYX("wwyx"), WWYY("wwyy"), WWYZ("wwyz"), WWYW("wwyw"),
    WWZX("wwzx"), WWZY("wwzy"), WWZZ("wwzz"), WWZW("wwzw"),
    WWWX("wwwx"), WWWY("wwwy"), WWWZ("wwwz"), WWWW("wwww"),
    RRRR("rrrr"), RRRG("rrrg"), RRRB("rrrb"), RRRA("rrra"),
    RRGR("rrgr"), RRGG("rrgg"), RRGB("rrgb"), RRGA("rrga"),
    RRBR("rrbr"), RRBG("rrbg"), RRBB("rrbb"), RRBA("rrba"),
    RRAR("rrar"), RRAG("rrag"), RRAB("rrab"), RRAA("rraa"),
    RGRR("rgrr"), RGRG("rgrg"), RGRB("rgrb"), RGRA("rgra"),
    RGGR("rggr"), RGGG("rggg"), RGGB("rggb"), RGGA("rgga"),
    RGBR("rgbr"), RGBG("rgbg"), RGBB("rgbb"), RGBA("rgba"),
    RGAR("rgar"), RGAG("rgag"), RGAB("rgab"), RGAA("rgaa"),
    RBRR("rbrr"), RBRG("rbrg"), RBRB("rbrb"), RBRA("rbra"),
    RBGR("rbgr"), RBGG("rbgg"), RBGB("rbgb"), RBGA("rbga"),
    RBBR("rbbr"), RBBG("rbbg"), RBBB("rbbb"), RBBA("rbba"),
    RBAR("rbar"), RBAG("rbag"), RBAB("rbab"), RBAA("rbaa"),
    RARR("rarr"), RARG("rarg"), RARB("rarb"), RARA("rara"),
    RAGR("ragr"), RAGG("ragg"), RAGB("ragb"), RAGA("raga"),
    RABR("rabr"), RABG("rabg"), RABB("rabb"), RABA("raba"),
    RAAR("raar"), RAAG("raag"), RAAB("raab"), RAAA("raaa"),
    GRRR("grrr"), GRRG("grrg"), GRRB("grrb"), GRRA("grra"),
    GRGR("grgr"), GRGG("grgg"), GRGB("grgb"), GRGA("grga"),
    GRBR("grbr"), GRBG("grbg"), GRBB("grbb"), GRBA("grba"),
    GRAR("grar"), GRAG("grag"), GRAB("grab"), GRAA("graa"),
    GGRR("ggrr"), GGRG("ggrg"), GGRB("ggrb"), GGRA("ggra"),
    GGGR("gggr"), GGGG("gggg"), GGGB("gggb"), GGGA("ggga"),
    GGBR("ggbr"), GGBG("ggbg"), GGBB("ggbb"), GGBA("ggba"),
    GGAR("ggar"), GGAG("ggag"), GGAB("ggab"), GGAA("ggaa"),
    GBRR("gbrr"), GBRG("gbrg"), GBRB("gbrb"), GBRA("gbra"),
    GBGR("gbgr"), GBGG("gbgg"), GBGB("gbgb"), GBGA("gbga"),
    GBBR("gbbr"), GBBG("gbbg"), GBBB("gbbb"), GBBA("gbba"),
    GBAR("gbar"), GBAG("gbag"), GBAB("gbab"), GBAA("gbaa"),
    GARR("garr"), GARG("garg"), GARB("garb"), GARA("gara"),
    GAGR("gagr"), GAGG("gagg"), GAGB("gagb"), GAGA("gaga"),
    GABR("gabr"), GABG("gabg"), GABB("gabb"), GABA("gaba"),
    GAAR("gaar"), GAAG("gaag"), GAAB("gaab"), GAAA("gaaa"),
    BRRR("brrr"), BRRG("brrg"), BRRB("brrb"), BRRA("brra"),
    BRGR("brgr"), BRGG("brgg"), BRGB("brgb"), BRGA("brga"),
    BRBR("brbr"), BRBG("brbg"), BRBB("brbb"), BRBA("brba"),
    BRAR("brar"), BRAG("brag"), BRAB("brab"), BRAA("braa"),
    BGRR("bgrr"), BGRG("bgrg"), BGRB("bgrb"), BGRA("bgra"),
    BGGR("bggr"), BGGG("bggg"), BGGB("bggb"), BGGA("bgga"),
    BGBR("bgbr"), BGBG("bgbg"), BGBB("bgbb"), BGBA("bgba"),
    BGAR("bgar"), BGAG("bgag"), BGAB("bgab"), BGAA("bgaa"),
    BBRR("bbrr"), BBRG("bbrg"), BBRB("bbrb"), BBRA("bbra"),
    BBGR("bbgr"), BBGG("bbgg"), BBGB("bbgb"), BBGA("bbga"),
    BBBR("bbbr"), BBBG("bbbg"), BBBB("bbbb"), BBBA("bbba"),
    BBAR("bbar"), BBAG("bbag"), BBAB("bbab"), BBAA("bbaa"),
    BARR("barr"), BARG("barg"), BARB("barb"), BARA("bara"),
    BAGR("bagr"), BAGG("bagg"), BAGB("bagb"), BAGA("baga"),
    BABR("babr"), BABG("babg"), BABB("babb"), BABA("baba"),
    BAAR("baar"), BAAG("baag"), BAAB("baab"), BAAA("baaa"),
    ARRR("arrr"), ARRG("arrg"), ARRB("arrb"), ARRA("arra"),
    ARGR("argr"), ARGG("argg"), ARGB("argb"), ARGA("arga"),
    ARBR("arbr"), ARBG("arbg"), ARBB("arbb"), ARBA("arba"),
    ARAR("arar"), ARAG("arag"), ARAB("arab"), ARAA("araa"),
    AGRR("agrr"), AGRG("agrg"), AGRB("agrb"), AGRA("agra"),
    AGGR("aggr"), AGGG("aggg"), AGGB("aggb"), AGGA("agga"),
    AGBR("agbr"), AGBG("agbg"), AGBB("agbb"), AGBA("agba"),
    AGAR("agar"), AGAG("agag"), AGAB("agab"), AGAA("agaa"),
    ABRR("abrr"), ABRG("abrg"), ABRB("abrb"), ABRA("abra"),
    ABGR("abgr"), ABGG("abgg"), ABGB("abgb"), ABGA("abga"),
    ABBR("abbr"), ABBG("abbg"), ABBB("abbb"), ABBA("abba"),
    ABAR("abar"), ABAG("abag"), ABAB("abab"), ABAA("abaa"),
    AARR("aarr"), AARG("aarg"), AARB("aarb"), AARA("aara"),
    AAGR("aagr"), AAGG("aagg"), AAGB("aagb"), AAGA("aaga"),
    AABR("aabr"), AABG("aabg"), AABB("aabb"), AABA("aaba"),
    AAAR("aaar"), AAAG("aaag"), AAAB("aaab"), AAAA("aaaa"),
    SSSS("ssss"), SSST("ssst"), SSSP("sssp"), SSSQ("sssq"),
    SSTS("ssts"), SSTT("sstt"), SSTP("sstp"), SSTQ("sstq"),
    SSPS("ssps"), SSPT("sspt"), SSPP("sspp"), SSPQ("sspq"),
    SSQS("ssqs"), SSQT("ssqt"), SSQP("ssqp"), SSQQ("ssqq"),
    STSS("stss"), STST("stst"), STSP("stsp"), STSQ("stsq"),
    STTS("stts"), STTT("sttt"), STTP("sttp"), STTQ("sttq"),
    STPS("stps"), STPT("stpt"), STPP("stpp"), STPQ("stpq"),
    STQS("stqs"), STQT("stqt"), STQP("stqp"), STQQ("stqq"),
    SPSS("spss"), SPST("spst"), SPSP("spsp"), SPSQ("spsq"),
    SPTS("spts"), SPTT("sptt"), SPTP("sptp"), SPTQ("sptq"),
    SPPS("spps"), SPPT("sppt"), SPPP("sppp"), SPPQ("sppq"),
    SPQS("spqs"), SPQT("spqt"), SPQP("spqp"), SPQQ("spqq"),
    SQSS("sqss"), SQST("sqst"), SQSP("sqsp"), SQSQ("sqsq"),
    SQTS("sqts"), SQTT("sqtt"), SQTP("sqtp"), SQTQ("sqtq"),
    SQPS("sqps"), SQPT("sqpt"), SQPP("sqpp"), SQPQ("sqpq"),
    SQQS("sqqs"), SQQT("sqqt"), SQQP("sqqp"), SQQQ("sqqq"),
    TSSS("tsss"), TSST("tsst"), TSSP("tssp"), TSSQ("tssq"),
    TSTS("tsts"), TSTT("tstt"), TSTP("tstp"), TSTQ("tstq"),
    TSPS("tsps"), TSPT("tspt"), TSPP("tspp"), TSPQ("tspq"),
    TSQS("tsqs"), TSQT("tsqt"), TSQP("tsqp"), TSQQ("tsqq"),
    TTSS("ttss"), TTST("ttst"), TTSP("ttsp"), TTSQ("ttsq"),
    TTTS("ttts"), TTTT("tttt"), TTTP("tttp"), TTTQ("tttq"),
    TTPS("ttps"), TTPT("ttpt"), TTPP("ttpp"), TTPQ("ttpq"),
    TTQS("ttqs"), TTQT("ttqt"), TTQP("ttqp"), TTQQ("ttqq"),
    TPSS("tpss"), TPST("tpst"), TPSP("tpsp"), TPSQ("tpsq"),
    TPTS("tpts"), TPTT("tptt"), TPTP("tptp"), TPTQ("tptq"),
    TPPS("tpps"), TPPT("tppt"), TPPP("tppp"), TPPQ("tppq"),
    TPQS("tpqs"), TPQT("tpqt"), TPQP("tpqp"), TPQQ("tpqq"),
    TQSS("tqss"), TQST("tqst"), TQSP("tqsp"), TQSQ("tqsq"),
    TQTS("tqts"), TQTT("tqtt"), TQTP("tqtp"), TQTQ("tqtq"),
    TQPS("tqps"), TQPT("tqpt"), TQPP("tqpp"), TQPQ("tqpq"),
    TQQS("tqqs"), TQQT("tqqt"), TQQP("tqqp"), TQQQ("tqqq"),
    PSSS("psss"), PSST("psst"), PSSP("pssp"), PSSQ("pssq"),
    PSTS("psts"), PSTT("pstt"), PSTP("pstp"), PSTQ("pstq"),
    PSPS("psps"), PSPT("pspt"), PSPP("pspp"), PSPQ("pspq"),
    PSQS("psqs"), PSQT("psqt"), PSQP("psqp"), PSQQ("psqq"),
    PTSS("ptss"), PTST("ptst"), PTSP("ptsp"), PTSQ("ptsq"),
    PTTS("ptts"), PTTT("pttt"), PTTP("pttp"), PTTQ("pttq"),
    PTPS("ptps"), PTPT("ptpt"), PTPP("ptpp"), PTPQ("ptpq"),
    PTQS("ptqs"), PTQT("ptqt"), PTQP("ptqp"), PTQQ("ptqq"),
    PPSS("ppss"), PPST("ppst"), PPSP("ppsp"), PPSQ("ppsq"),
    PPTS("ppts"), PPTT("pptt"), PPTP("pptp"), PPTQ("pptq"),
    PPPS("ppps"), PPPT("pppt"), PPPP("pppp"), PPPQ("pppq"),
    PPQS("ppqs"), PPQT("ppqt"), PPQP("ppqp"), PPQQ("ppqq"),
    PQSS("pqss"), PQST("pqst"), PQSP("pqsp"), PQSQ("pqsq"),
    PQTS("pqts"), PQTT("pqtt"), PQTP("pqtp"), PQTQ("pqtq"),
    PQPS("pqps"), PQPT("pqpt"), PQPP("pqpp"), PQPQ("pqpq"),
    PQQS("pqqs"), PQQT("pqqt"), PQQP("pqqp"), PQQQ("pqqq"),
    QSSS("qsss"), QSST("qsst"), QSSP("qssp"), QSSQ("qssq"),
    QSTS("qsts"), QSTT("qstt"), QSTP("qstp"), QSTQ("qstq"),
    QSPS("qsps"), QSPT("qspt"), QSPP("qspp"), QSPQ("qspq"),
    QSQS("qsqs"), QSQT("qsqt"), QSQP("qsqp"), QSQQ("qsqq"),
    QTSS("qtss"), QTST("qtst"), QTSP("qtsp"), QTSQ("qtsq"),
    QTTS("qtts"), QTTT("qttt"), QTTP("qttp"), QTTQ("qttq"),
    QTPS("qtps"), QTPT("qtpt"), QTPP("qtpp"), QTPQ("qtpq"),
    QTQS("qtqs"), QTQT("qtqt"), QTQP("qtqp"), QTQQ("qtqq"),
    QPSS("qpss"), QPST("qpst"), QPSP("qpsp"), QPSQ("qpsq"),
    QPTS("qpts"), QPTT("qptt"), QPTP("qptp"), QPTQ("qptq"),
    QPPS("qpps"), QPPT("qppt"), QPPP("qppp"), QPPQ("qppq"),
    QPQS("qpqs"), QPQT("qpqt"), QPQP("qpqp"), QPQQ("qpqq"),
    QQSS("qqss"), QQST("qqst"), QQSP("qqsp"), QQSQ("qqsq"),
    QQTS("qqts"), QQTT("qqtt"), QQTP("qqtp"), QQTQ("qqtq"),
    QQPS("qqps"), QQPT("qqpt"), QQPP("qqpp"), QQPQ("qqpq");

    private final String mValue;

    D44(String value) {
      mValue = value;
    }

    @Override
    public String toString() {
      return mValue;
    }
  }
}
