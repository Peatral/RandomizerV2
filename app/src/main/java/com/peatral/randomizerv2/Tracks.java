package com.peatral.randomizerv2;

public enum Tracks {
    TRACK_MARIOKARTSTADIUM("mariokartstadium","mushroomcup","newtracks"),
    TRACK_WATERPARK("waterpark","mushroomcup","newtracks"),
    TRACK_SWEETSWEETCANYON("sweetsweetcanyon","mushroomcup","newtracks"),
    TRACK_THWOMPRUINS("thwompruins","mushroomcup","newtracks"),
    TRACK_MARIOCIRCUIT("mariocircuit","flowercup","newtracks"),
    TRACK_TOADHARBOR("toadharbor","flowercup","newtracks"),
    TRACK_TWISTEDMANSION("twistedmansion","flowercup","newtracks"),
    TRACK_SHYGUYFALLS("shyguyfalls","flowercup","newtracks"),
    TRACK_SUNSHINEAIRPORT("sunshineairport","starcup","newtracks"),
    TRACK_DOLPHINSHOALS("dolphinshoals","starcup","newtracks"),
    TRACK_ELECTRODROME("electrodrome","starcup","newtracks"),
    TRACK_MOUNTWARIO("mountwario","starcup","newtracks"),
    TRACK_CLOUDTOPCRUISE("cloudtopcruise","specialcup","newtracks"),
    TRACK_BONEDRYDUNES("bonedrydunes","specialcup","newtracks"),
    TRACK_BOWSERSCASTLE("bowserscastle","specialcup","newtracks"),
    TRACK_RAINBOWROAD("rainbowroad","specialcup","newtracks"),
    TRACK_WIIMOOMOOMEADOWS("wiimoomoomeadows","shellcup","retrotracks"),
    TRACK_GBAMARIOCIRCUIT("gbamariocircuit","shellcup","retrotracks"),
    TRACK_DSCHEEPCHEEPBEACH("dscheepcheepbeach","shellcup","retrotracks"),
    TRACK_N64TOADSTURNPIKE("n64toadsturnpike","shellcup","retrotracks"),
    TRACK_GCNDRYDRYDESERT("gcndrydrydesert","bananacup","retrotracks"),
    TRACK_SNESDONUTPLAINS3("snesdonutplains3","bananacup","retrotracks"),
    TRACK_N64ROYALRACEWAY("n64royalraceway","bananacup","retrotracks"),
    TRACK_3DSDKJUNGLE("3dsdkjungle","bananacup","retrotracks"),
    TRACK_DSWARIOSTADIUM("dswariostadium","leafcup","retrotracks"),
    TRACK_GCNSHERBETLAND("gcnsherbetland","leafcup","retrotracks"),
    TRACK_3DSMUSICPARK("3dsmusicpark","leafcup","retrotracks"),
    TRACK_N64YOSHIVALLEY("n64yoshivalley","leafcup","retrotracks"),
    TRACK_DSTICKTOCKCLOCK("dsticktockclock","lightningcup","retrotracks"),
    TRACK_3DSPIRANHAPLANTSLIDE("3dspiranhaplantslide","lightningcup","retrotracks"),
    TRACK_WIIGRUMBLEVOLCANO("wiigrumblevolcano","lightningcup","retrotracks"),
    TRACK_N64RAINBOWROAD("n64rainbowroad","lightningcup","retrotracks"),
    TRACK_GCNYOSHISCIRCUIT("gcnyoshiscircuit","eggcup","dlctracks"),
    TRACK_EXCITEBIKEARENA("excitebikearena","eggcup","dlctracks"),
    TRACK_DRAGONDRIFTWAY("dragondriftway","eggcup","dlctracks"),
    TRACK_MUTECITY("mutecity","eggcup","dlctracks"),
    TRACK_WIIWARIOSGOLDMINE("wiiwariosgoldmine","triforcecup","dlctracks"),
    TRACK_SNESRAINBOWROAD("snesrainbowroad","triforcecup","dlctracks"),
    TRACK_ICEICEOUTPOST("iceiceoutpost","triforcecup","dlctracks"),
    TRACK_HYRULECIRCUIT("hyrulecircuit","triforcecup","dlctracks"),
    TRACK_3DSNEOBOWSERCITY("3dsneobowsercity","bellcup","dlctracks"),
    TRACK_GBARIBBONROAD("gbaribbonroad","bellcup","dlctracks"),
    TRACK_SUPERBELLSUBWAY("superbellsubway","bellcup","dlctracks"),
    TRACK_BIGBLUE("bigblue","bellcup","dlctracks"),
    TRACK_GCNBABYPARK("gcnbabypark","crossingcup","dlctracks"),
    TRACK_GBACHEESELAND("gbacheeseland","crossingcup","dlctracks"),
    TRACK_WILDWOODS("wildwoods","crossingcup","dlctracks"),
    TRACK_ANIMALCROSSING("animalcrossing","crossingcup","dlctracks");

    private String name;
    private String cup;
    private String type;

    Tracks(String name, String cup, String type) {
        this.name = name;
        this.cup = cup;
        this.type = type;
    }

    public Tracks getFromName(String name){
        for (Tracks t : values()){
            if(t.getName() == name){
                return t;
            }
        }
        return Tracks.TRACK_MARIOKARTSTADIUM;
    }

    public String getName() {
        return name;
    }

    public String getCup() {
        return cup;
    }

    public String getType() {
        return type;
    }
}
