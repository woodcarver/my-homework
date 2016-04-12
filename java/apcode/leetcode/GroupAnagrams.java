package leetcode;
import java.util.*;
//关键点：每个字段串能忽略顺序呢映射成一个唯一识别码——想想还有没有忽略字符串顺序标识组合的方法
//方法一：素数乘积法——每个字符映射成一个素数，字符串映射成这些字符的乘积，缺点当字符过多或者字符串过长，会有溢出的问题
//方法二：bitmap——每个字符给一个bit位置，字符串就是这些字符位置开启和关闭的串，
//		缺点表示bitmap的变量类型如果选择int或者long的话，对输入集合有大小限制，
//      还有需要知道字符集合的大小，以分配bit位置和字符的对应关系。
//
// 错误记录： 素数测试有误3次，1.(n%i)==0写成了(n%i)>0; 2. lastNum=29写成了lastNum=2; 3.!isPrime(testNum) 写成了isPrime(testNum)
public class GroupAnagrams {
    HashMap<Character,Integer> charMap=new HashMap<Character,Integer>();
    HashMap<Long,Integer> productMap=new HashMap<Long,Integer>();
    List<List<String>> output=new ArrayList<List<String>>();
    public PrimeNum prime=new PrimeNum();

    int biggestSlot=0;
    private int getNum(char c){
    	int num;
    	if(!charMap.containsKey(c)){
    		num=prime.next();
    		charMap.put(c, num);
    	}else{
    		num=charMap.get(c);
    	}
    	
    	return num;
    }
    private int getNum2(char c){
    	if(0==charMap.size()){
    		initCharMap();
    	}
    	return charMap.get(c);
    }
    private void initCharMap(){
    	//2,3,5,7,11,13,17,19,23,29
    	System.out.println("char map inital");
    	charMap.put('a', 2);
    	charMap.put('b', 3);
    	charMap.put('c', 5);
    	charMap.put('d', 7);
    	charMap.put('e', 11);
    	charMap.put('f', 13);
    	charMap.put('g', 17);
    	charMap.put('h', 19);
    	charMap.put('i', 23);
    	charMap.put('j', 29);
    	charMap.put('k', 31);
    	charMap.put('l', 37);
    	charMap.put('m', 41);
    	charMap.put('n', 43);
    	charMap.put('o', 47);
    	charMap.put('p', 89);
    	charMap.put('r', 53);
    	charMap.put('s', 97);
    	charMap.put('t', 59);
    	charMap.put('u', 61);
    	charMap.put('v', 101);
    	charMap.put('w', 103);
    	charMap.put('x', 71);
    	charMap.put('y', 73);
    	charMap.put('z', 79);
    	charMap.put('q', 83);

    }
    private int getSlot(long product){
    	int slot;
    	if(!productMap.containsKey(product)){
    		slot=biggestSlot;//特别注意slot和biggestSlot的关系
    		biggestSlot++;
    		productMap.put(product, slot);
    		output.add(new ArrayList<String>());
    	}else{
    		slot=productMap.get(product);
    	}
    	return slot;
    }
	public List<List<String>> groupAnagrams(String[] strs) {
	     long product=1;
	     int slot=0;
	     for(String str:strs){
	    	 product=1;
	    	 for(int i=0; i<str.length(); i++){
//	    		 System.out.println(str.charAt(i)+":"+getNum(str.charAt(i)));
	    		 product*=getNum2(str.charAt(i));
	    	 }
	    	 System.out.println(str+":"+product);
	    	 slot=getSlot(product);
	    	 output.get(slot).add(str);
	     }
	     
	     //给结果排序,不能用set，会去重同时还会把空串给去掉
	     for(List<String> list:output){
	    	 Collections.sort(list);
	     }
	     return output;
	}
	private void printOutput(){
		for(List<String> list:output){
			System.out.println(list);
		}
	}
	public static void main(String[] args){
		//String[] strs={"eat", "tea", "tan", "ate", "nat", "bat","as","sa","pppppppppppppptppppppppppppppt","ppppppppppppppttpppppppppppppp","pppppppppppppptppppppptppppppp"};
		//String[] strs={"eat", "tea", "tan","ate","","",""};
//		String[] strs={"poe","why","man","rio","tom","mob","jar","hon","den","red","beg","her","saw","shy","bee","ram","ina","bun","brr","gen","fry","aye","che","pop","cod","ivy","ham","pyx","zed","tor","why","pun","pup","mid","lad","nov","nag","ike","jam","rat","sky","lob","pis","win","and","toe","man","flu","van","lid","guy","ltd","spa","wyo","law","ian"};
		//String[] strs={"mod","mop","pip","tug","hop","dog","met","zoe","axe","mug","fdr","for","fro","fdr","pap","ray","lop","nth","old","eva","ell","mci","wee","ind","but","all","her","lew","ken","awl","law","rim","zit","did","yam","not","ref","lao","gab","sax","cup","new","job","new","del","gap","win","pot","lam","mgm","yup","hon","khz","sop","has","era","ark"};
		String[] strs={"incentive","countersink","pickaxing","explicit","unethical","seoul","gyrates","dismounting","dartboard","socialism","sissiest","radiotherapist","sprawl","hems","raggedier","conscripted","repealed","implanted","coverage","traitorous","barmaid","pointier","huntress","summers","finnish","mouthed","mamore","lemur","osteoporosis","frowziest","sop","comical","speedup","oasis","peon","testers","stances","chuckles","childes","consensuses","rastafarian","battlement","christies","urbanized","penitence","replenishment","disperse","kibosh","combatting","repealing","guise","grassiest","grafts","waddle","pigsties","moneymaking","polite","reprogramming","audaciously","gorier","blaze","yaqui","racially","inc","reupholstering","fez","lemaitre","nineties","pedal","deferred","tranquil","surveyor","narrow","hopper","solemnize","federate","trisecting","cravat","lille","biography","land","jazzing","rhomboids","nudes","mundanely","greenbacks","chattel","deceleration","devoting","itemizing","routing","jukebox","passkey","infants","prerecorded","fuzzing","gandhi","roseate","bandit","prong","bowels","bounciest","misdeeds","unseemliness","emceed","later","hailed","crotchet","steepness","jot","improves","eucharistic","natalia","exalt","performers","predictor","voyeurism","fedex","activate","crypt","magics","treats","viewed","chauffeur","ladybug","peppiest","seismic","microscopes","liners","wraps","footman","tape","daubers","aerial","sparer","politic","shamed","decal","ayrshire","ruggeder","washcloths","neurologists","singsong","kaitlin","conn","monopolizes","sunroof","zonked","overall","celsius","fins","caparison","imus","enfranchises","microns","promulgates","aspartame","fails","decamps","spiciness","impieties","starring","receded","skying","suetonius","pornographers","intellectualized","truckled","abdications","openness","principally","sellers","complaining","jeopardizing","concerto","fibula","cliffs","aorta","inhale","transgressions","copywriter","understands","coccis","disperses","fraying","strengths","macaulay","mitigation","competences","ides","linked","tawnier","hallucinate","dairymen","puffier","matchless","wheeling","drily","nephews","clandestinely","tsingtao","disarraying","headier","experts","yippee","repellent","tributary","clannish","dumpsters","genealogies","ballooned","denouements","hymned","length","sasses","uncontrollably","fortune","wastefully","overproducing","sickening","headboard","burrs","prohibits","keogh","outs","lamer","repressed","rowdyism","transgressed","euros","amenity","garfunkel","rather","noseys","lampoons","raja","impermanence","heaters","shakespeareans","judiciaries","tweaked","extrapolations","succeeded","jowls","vociferation","roadways","herb","inactivity","registrars","checks","deservedly","heehaws","apologetics","fatness","aced","promote","atriums","deidre","redound","litterbug","barehanded","scheduled","flakiest","twirler","graduated","coccus","gadfly","unforeseeable","emendation","woodsman","wiriest","tangelo","weakening","intimidating","sternness","reiteration","wicca","heresies","inches","encodes","sidebar","starking","wastelands","pepsi","emigrate","stuttgart","shindigs","pansy","chintzy","limbo","disbarred","gallivant","hallmarked","respiratory","eminences","unabated","affirms","twinned","wordiness","installing","donn","westerly","interfaces","benton","mussy","identical","sulfates","starvings","woodwork","hyphenated","catechizing","transition","dramatist","demilitarize","buber","spoonful","laconic","undervaluing","inestimably","severer","improvidence","stoneware","cholera","peru","rearm","recluses","diaz","tablespoonsful","sandals","cinderella","insubordination","sires","stern","jeremiah","snowbound","ramifies","lexicographer","skimpiest","predetermining","lapidary","dentists","upbeats","marketplace","ploughs","esteem","miserably","overreaction","unlikelihood","kinswoman","averts","remonstrating","asinine","amortize","wasps","coal","tenderfoot","subplot","coyotes","sauntering","rices","fraughts","decoded","millipedes","knocking","homework","furor","expansionists","j","emergency","sharon","provisoes","president","assuaging","curtail","indigestion","liming","cryptozoic","auguring","indenture","magisterially","groped","ensue","steaming","mutinous","incomplete","yuletide","nomenclatures","hundredth","jimmies","irk","common","wetly","volley","summary","obsequies","granola","forests","allege","informal","shapeless","earplugs","disunite","insinuations","junked","riverbeds","georgian","brushed","sequenced","bucketfuls","spiel","humors","screwed","capitulating","downturns","intimidates","refueling","mingled","arisen","dragnet","aftereffects","practised","obduracy","canon","penning","inflammatory","wisher","actuate","kari","actuating","reupholstered","successes","inborn","canvasbacks","incapacitated","aristocratic","britt","lazying","hymnals","tyroes","tamper","graduate","franc","seasons","pompom","throeing","deviate","leveling","inaccurate","impaling","trouping","restoring","deduces","perforating","artie","alleghenies","crusader","rosemary","chastest","educational","caveatting","ablest","doorstepped","strange","eccentricities","sublimity","afire","humbugging","jots","ordinary","foyer","kayak","snake","wheal","dispensations","transponders","zing","pit","meowing","improved","crowd","nonfatal","epsilon","nodding","cyclones","imbibed","rebroadcasting","quibbles","moisturizes","zippy","braille","visaing","abetters","lurked","snafus","moppet","lemon","wifeliest","stuarts","boyish","reapportion","kneecaps","stymying","villagers","obscenity","bumble","zeroing","objectionably","connolly","memorial","clanging","gallstones","dibbled","outlasting","mendez","reproaching","antagonized","sisterhoods","hunchbacks","superimposed","siphon","professionally","spermicides","lebanon","facing","pierces","philosophically","mcdowell","rotated","tic","correspondents","nonplussed","litchi","ascots","millennial","booked","immunize","prefabricate","spumoni","odyssey","fey","bunched","olen","regularizing","superconductors","aside","infirmary","allaying","saturdays","caucasian","doreen","france","speeding","wretched","slovakia","chiselled","peseta","tho","gales","loincloths","shock","readers","boarder","entryway","toning","transitively","preponderances","internships","rating","pelleting","audition","amusement","theed","cocked","toddler","rusty","kinfolk","frames","gorgonzola","overrate","lightweight","bayous","populists","insensible","bandoliers","travelogs","tactically","weatherproofs","sleigh","develops","oxygenate","relaid","mimi","wheezier","chill","salween","theatre","vitalizes","nun","olives","offings","tricycling","tipper","kindly","bellies","sixteenths","ganglia","recombining","eastward","minestrone","fairgrounds","undelivered","scapula","breadth","groupie","ophelia","adrenaline","dork","autographing","watersides","sent","socks","somnolence","systematize","signings","rachmaninoff","santa","snacks","leno","spurs","heliotropes","silenced","roundhouses","illegitimacy","hedgehog","wet","depressed","eris","pure","pouncing","obtuseness","caulk","openly","kerosene","grille","buccaneers","oar","modals","playacts","shroud","plowshares","iciness","retort","jaunt","identities","mellower","redesigning","molest","forlorner","guacamole","cohan","refocus","dossier","interrogating","cants","diligence","stomps","enjoy","awe","loggerhead","csonka","inversions","den","lunged","alkaid","palming","delivers","proprietorship","groveler","choreographs","instals","merton","cyclops","augmentation","manipulated","nasal","trousseaux","critiques","arenas","stale","suspect","behoove","propose","cellulite","purification","leah","polestar","potato","gladlier","sunders","entreat","herbalist","filthier","skinflints","asseverating","insulted","mildly","modulate","charges","libyans","weekended","rodeos","allegedly","tarawa","valedictorians","spacy","spontaneously","yore","classify","centigrade","videoing","airdropping","dejection","pierce","imagined","isabel","reiterates","blazing","stuck","deceasing","netherlands","citric","tormented","monotoning","bestowals","betrayals","adoptions","scrolls","sex","idol","whaler","telephotos","conjugation","abductions","bundestag","disruptions","nouakchott","stuffiest","goodly","dissatisfying","anglicize","flowing","identifying","depots","stoicism","thessalonian","hundred","whetstone","defroster","headwaiter","feisty","inhibited","reamed","demonstrator","accursed","hector","interchanges","erotically","sportscaster","gentlewomen","teabag","smelts","disorganization","conveniently","maugham","hatchery","regards","arguably","govern","boulevards","niches","gristlier","obtrude","catharses","throughway","stoppered","drinkers","indeterminacy","vacuums","plainest","acuff","eases","defined","splurge","severing","griddles","thermoplastics","dilution","vinegary","straights","testament","sticks","shanghai","childproofing","clobbers","taco","castro","arrests","fetter","discomposing","timbres","wondrous","pullmans","amusements","telephonic","manning","inhabiting","finery","admires","defensively","fiord","incriminate","oust","collapse","deliquescent","bulwark","consciousness","roguery","beefburger","amounted","pheasants","archdioceses","vote","gujarati","forwards","waggling","tanned","liberalization","chap","finishing","catawba","anus","farina","butterflying","folks","crumple","criticize","unswerving","snack","gentrification","enthroning","drearies","marietta","farley","recede","incise","electrodes","dirac","corn","livest","coughs","noreen","rapscallion","codeine","acidly","confiscates","dislikes","administrators","valid","prep","legated","wherewithal","subjugating","wallabies","boning","creakiest","erotica","neophytes","drowses","bushmen","basis","regattas","melancholy","clubfeet","there","generalized","rotunding","condone","hemlines","bantered","vertebral","ruby","companions","postmarked","aping","finalized","prado","mutilations","partied","isiah","desk","calcines","agreeing","bode","cygnet","downsizing","naiads","preambled","recife","randall","gillespie","convalescent","victories","obsessed","leaders","strips","parmesan","germicide","clarion","pictograph","nutshells","bungalows","hagged","colosseum","ferraro","recapitulations","labored","acrostic","dynamics","bucked","betaking","pragmatically","gobbed","glossy","congresses","rewindable","trout","renovates","mattered","davenport","stenographer","nudism","deduced","unattributed","equally","wrestled","sequoia","measurable","lamb","babe","concourses","creamer","fascination","steroids","deplaned","germinated","progressed","swashbuckling","pools","officemax","brewers","banjul","thong","periodic","grub","typescript","lemurs","excreta","seeps","clearinghouses","isolating","particularized","thallium","waistcoat","misread","extensiveness","violations","insistent","zany","newses","predominate","enunciating","ultras","burlap","dowelled","elision","person","minneapolis","edification","endued","asseverates","meeter","iceberg","mexican","end","wale","veronese","ida","earshot","tunnels","debuting"};
		GroupAnagrams ga=new GroupAnagrams();
		ga.groupAnagrams(strs);
		ga.printOutput();
//		System.out.println(ga.getPrime(11));
//		System.out.println(ga.prime.isPrime(31));
	}
}
class PrimeNum{
    public int pos=0;
    int cacheSize=10;
    int lastNum=29;
    int[] primeNum={2,3,5,7,11,13,17,19,23,29};//可以更长
    public int next(){
    	if(pos>=cacheSize){
    		lastNum=genNext();
    	}else{
    		lastNum=primeNum[pos];
    	}
    	pos++;
    	return lastNum;
    }
    private int genNext(){
    	int testNum=lastNum+2;
    	while(!isPrime(testNum)){
    		System.out.println(testNum);
    		testNum+=2;
    	}     		
    	return testNum;
    }
    public boolean isPrime(int n){
    	if((n%2)==0) return false;//偶数
    	for(int i=3; i<n/2; i++){
    		if((n%i)==0)
    			return false;
    	}
    		
    	return true;
    }
}
