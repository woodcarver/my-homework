package leetcode;
import java.util.*;
//问题关键点：（这个问题拆分成两个子问题）1. 怎么确定两个字符串有公共字符；2. 怎么找最大的乘积
//问题一:使用bitmap来确定
//问题二：这里使用了暴力破解法，打败了4%的提交（哎~，需要优化）
//看了后续的解题，发现不用hashtable,速度能从300ms到90ms
public class MaxProductOfWordLengths {
	private HashMap<String,Integer> bitDict=new HashMap<String,Integer>();
    public String w1=null;
	public String w2=null;
	//bitmap[i]-->bitmap[i-'a'],可以不用bitmap数组，直接通过i来计算就可以，比如 1<< (i-'a')
//	int[] bitmap={1,2,4,8,16,32,64,128,256,512,1024,2048,4096,};
	private boolean hasCommonLetters(String w1,String w2){
		int w1Bitmap=getBitmap(w1);
		int w2Bitmap=getBitmap(w2);
		return (w1Bitmap&w2Bitmap)>0;
	}
	private int getBitmap(String w){
		int bitmap=0;
		if(!bitDict.containsKey(w)){
			for(int i=0,len=w.length(); i<len;i++){
				bitmap=bitmap|(1 << (w.charAt(i)-'a'));
			}
			bitDict.put(w, bitmap);
//			System.out.printf("%s:\\x%x, %d\n",w,bitmap,bitmap);
		}else{
			bitmap=bitDict.get(w);
		}
		return bitmap;
	}
    public int maxProduct1(String[] words) {
        int maxP=0;
        int p=0;
        int len=words.length;
        for(int i=0; i<len-1; i++){
        	String pivot=words[i];
        	for(int j=1; j<len; j++){
        		System.out.println("Compare:"+pivot+"-"+words[j]+"-"+maxP);
        		if(!hasCommonLetters(pivot,words[j])){
            		System.out.println("Has no common");
        			p=pivot.length()*words[j].length();
        			if(maxP<=p){
        				maxP=p;
        				w1=pivot;
        				w2=words[j];
        			}
        		}
        	}
        }
        return maxP;
    }
    public int maxProduct2(String[] words) {
        int maxP=0;
        int p=0;
        int len=words.length;
        List<String> wordlist=Arrays.asList(words);
        Collections.sort(wordlist, new Comparator<String>(){
        	public int compare(String a,String b){
        		return a.length()-b.length();
        	}
        });
        
        for(int i=len-1; i>0; i--){
        	String pivot=wordlist.get(i);
        	for(int j=i-1; j>=0; j--){
        		String round=wordlist.get(j);
        		System.out.println("Compare:"+pivot+" --- "+round+"-"+maxP);
        		if(w2!=null && (round.length()<w2.length())){
        			System.out.println("length less than w2");
        			break;
        		}
        		if(!hasCommonLetters(pivot,round)){
            		System.out.println("Has no common");
        			p=pivot.length()*round.length();
        			if(maxP<=p){
        				maxP=p;
        				w1=pivot;
        				w2=round;
        			}
        		}
        	}
        }
        return maxP;
    }
    public int maxProduct3(String[] words) {
        int len=words.length;
        int[] bitmap=new int[len];
        for(int k=0; k<len; k++){
            String word=words[k];
            for(int s=0,wordLen=word.length(); s< wordLen; s++){
                bitmap[k]|=(1 << (word.charAt(s)-'a'));
            }
        }
        int maxP=0;
        int p=0;

        for(int i=0; i<len-1; i++){
        	String pivot=words[i];
        	for(int j=1; j<len; j++){
        		System.out.println("Compare:"+pivot+"-"+words[j]+"-"+maxP);
        		if(0==(bitmap[i]&bitmap[j])){
            		System.out.println("Has no common");
        			p=pivot.length()*words[j].length();
        			if(maxP<=p){
        				maxP=p;
        				w1=pivot;
        				w2=words[j];
        			}
        		}
        	}
        }
        return maxP;
    }
    public static void main(String[] args){
    	MaxProductOfWordLengths mp=new MaxProductOfWordLengths();
//    	String[] words={"a", "aa", "b", "a", "aaa", "bb"};
//    	String[] words={"a", "aa", "a","aaa"};
    	String[] words={"abcw", "baz", "foo", "bar", "xtfn", "abcdef"};
//    	String[] words={"bcedengp","jegidiicfohjimcccnkagmanbkkmbmlfabgammipaiepjnfi","condccpkmicalappldjbnlepdplggcmcnilkkinefgdmldegcjbimfaikfjldpoplcakdkglpnlnjkojhcglig","gnpddclieoneddfhojknjkkaehlkgegpfbnopjcnogcicokhlffd","edjnfgmoaningkmcfncodeganbmbhamoighbojdcjcdicipdobbcahil","hkjkfjkmaagcgpcimmljfghmkgekmkh","homffkkkipepcbmipn","ipkakmolkhecdddpdpcphafiaofgb","jeejnmdgokbmkmpdlkgiolkemenahkoidimoeidpagefpcokodcmjdjcbkaeaogmcbenhdcnegg","eapffhadafnnalkkobdbmpnnhfeg","opfknkinpknipgjhcjdgffjoippjcnfabdejn","fhnpojhnfiogkdgffgjc","nlhmgmigpbconl","jjlejejegngdljbgkkomfacjfjoemilaeofgdhiip","imagjifpapmgej","mjkdjbjmbhfbkbjnahlenhkdnnpplpenidahdkokeihmjjfojndiidlbkh","ihpnbcncpigllmpdekkjbdemobbdjemafeioiaoibgabakmmkklopoeppibdfhdlallcedldclmkhpmkkdg","plfpneanjljoebogkbfabdnkanodgljecaemcffnoicmdbgkkfma","afianbgmdff","bhobekifemheldcamckoccfdcnmdcmnfbodfldbhgoikplggehfokkolacadgoholcdbgcpgoanmmnmika","bnjklpaiklipbcbphhfjoibhhhljoppcfjdno","ickghheki","kacnagbficmhikbdeldcagg","laamledghhflcjjdfofdojkfbkl","aacafidfbpmimlclhcjignenchbdjcgfbdiegmohfloieajekfgcmamifbmkjnoaipcfmbejmkigaekcgemffemfcnkhhkkj","hlcdacefjodhllpbhkkokepmcdmmigclefoglahalejfiipjelmhkbhjblmbkgpimjbmplmgclfiifncckibpkjlnkbgcph","ohdadlaokhbhlgiepimmdmfegefpbkbcodnfebbcfakfijhnefg","lgeokomognpmfblbhekgnlpdohnpkoajionmjhhagenafmcdpgfiidlmnogigfipdpgjljlgakaoammekhmjhecnmghecdhljl","kmjkfdbibcnkinkagennlcfbajffdkgajloinjakombekjcbh","iofimmgddmjidfmmloofkdihlffaakapkhchmcclocdbandon","kpbmkbdfgobjebadjdhgkglakcbjpjkkpd","ghjahnmogdofblllfpdfbhohkmdingfmaofoflchbhpkjccffadnbd","lobkhmbgcjdpkemkijhddofpadcgabbfadkhmdmgippceaapeabhbemoejfhejhjncceckddmldpbdlmeljmcpbnaflolihhaojlp","nlcmhaaebniidhgammlkhpphglanihnmpchebbehfgjeegdkicbnnkgnaipdhiemigfgaoehedph","ecdnklamhjbkemhngkldlbbbkalepnlci","eokamphdgfobplgmnhcigcjalmbldgmfcgbppcicooneopcmkginofpcmgleeplobcd","fcmjlicbblmgpoedaglfpdmiajjnfjdlklfoapdigokimnjfnboecnlejeklcifhoomlnmlfimmjdpfieamjnmmcklcflmjd","dnfkclagfkofamkdijjhhklenidoahjeoieaddohalfdbkjgfeojdonnnpbpnkofhafohinchffihnkmcdbhnfano","jajcnlbijmpkhmninjpdjlaiabdpeinep","lpffenbmipjalpmocceaegabghkkbldmaaef","hfnjdgjcllcepegpgebbdiod","blofdnemnlghmhnklnagnjdhhncbligogkmcankckkicohicfocenpdfojnkkbfaihbfkceclmnj","blkojdljgkfbgoocagophckjhhaedplcecjhni","bfbmhobnbpjipgnohnnggkodlnhnpdnnjjodjjhjpoiblenkmmjfn","dakooapcaocdjjlohkhodebfljekmohmhaajabfmfpkmgenpcgejdmbpfhdmjcndidgnckejn","mcdplfipobaabeljedjjgffgibkjkfhcknlelcjploff","hfkkkgc","bckkahmfgpmllpjlegffdhmamighjeejfeibomlcflnf","hdlfkfnncgfggofkhbnnoogdhcjbmiecemdhblmaclcjjhfkd","hlleonkggomlodjiicahjpacmfmnjfhefghfjfjjhmollnmceckfl","fikabchalojpfe","eabebmiboflaoeilplfgbodlnpdjdiigcchpimdmgmimfdallmcig","piaaepcaceeiiejnliffickdbjnpbfggbdlcjcbggmbfegohjnnemkbjiaamcnplelnmlfjcab","olaganiohokddaakddildkphfmohabaneglhkapeeefkjdcb","obknlbdepjhofdgpjmbiefollhjdmkcgiembmcejbekalcdfpmdmpedblmodhkgmdnneheegkebc","fbelnnohpboamjkbddhlbcdllnlkgllkafiionmipaafpeibf","cdmdiekekdfagofmfnffnclcfjajllhojglodmpiaffddknehallhoojhjmgjifeofmagdjfengmpohechphnkghdjhmnldh","bfjegjnfdeipaogfeifnnpgpbblfdc","poenmckimbpifflnjcnejemhjdifmibniogckajponoamnoldfefdoglmhlldicekiecnmomp","nbchhcmpbialmi","jdebllagcaidnkmbbadfmnjjhiondmphngmmdkjmbmfecfdhhggmllmnjicdokbiekbpmkebhhcjjbolcppnomldpckdfij","dbbjijglkgaomhphbfpaejiiknadcoaopo","gdcakpbpcpeebncijnldoigfnm","njkhafdm","hahieknapoaidphimlidackdnhhdcnlfhhahjfdkacbdfdfeeogeklcfmmlfeiamjfpmbaiolgcgoncandlcabahfmdk","iagkkbchfijjnjggeiipikldigbinhdoppcaldcbognoakidpppchhdknedhlfmgmgmkombekejdigiimoefmahmahhhgdcm","jkpkfmpgfknpicjkodpjhmfhiikgdifapoldfojbahpbjbgdhiookababepckambnc","afcaeklcpjhjfampafogehdpiianokcpclohciajpmbaplgfpkjnkallpkoghglaccnfhnllkinigchddaak","hmkbgninnnmebgaghblo","npfpcpfekkcmb","dchjgnnbbdhllkdihdcdpkjdjmhnamjpfjalnambcpfmlahocemncmhoockmgedlegcfjnomdo","mh","kbbibjkm","fiopkjgdgpodnkpiekggelkhblbkeaoekdfpomgmcijfchankfmffiacafkcnbpp","pcemmimjiipcaimpipmpachpjlnpmdoneiag","ceoeipfliflfjnmpkngcoidgjicpapfaogiegfbhgpflnbjbgpencgaopmepkmfnkkjblphcgabbincgajfgelapmhjloefpogkpp","clockpjegiikjgkgjofgdgbjejknn","kjkjklbhfdmjmhnpmjoehniigbmofjmbmjkfmcahpgabamahpmimgicbfoemfomgdifoajmlnjbpbdfcilcidibhejkkbbhe","egkjneklne","aohokpnbnjchkkfipbfpjcbmnpebdklngnmfjaj","fhcnhadlelgajengoocmcjfmglpanhiciffmikjjgiboblmcbfomkdlkhpeoidfgemhccpogimlnpdemfia","jbemhdfkcoldoanpmedafkbpmnnfmhdmlncciojcmecoglahffmgkhgpofeegoebdnlfpebgapbflcma","hfhbipcidfdfgbhhmahnfgpliagmolhhfbmcjfgnhkdkale","nllcogabcbbfggkknkjimmbdlfinamdifcbgoffhekkhpfflpfnleccapgcepgankpnblofmphplpnomfdkdbcdhejp","dgpjgjnagacjfhlkhnmpfbhcplfehoolafmpieoh","mcjhhgajmclkoiobmgaaellbekaadlkopgbkobmmipeollfcjgo","lhnlcgljdpcbcjmncpiojli","obcddnfdiogpchomf","kjhkbnpcphmpnpilkhbjmeohodcoonanlmniifpiiiipcegagnhpbpfadgo","cjbakbbjaikfjnbimkeifcoinhhkcogokknajeagmfkcldfijhhfapnnjnljnecknlgdmapnlhffdndahoj","hdpflnimifempccgjolbdbdalfaapfijpmjnkaedkgmbpanmilleeahmnpiipna","cpjkhblnleglklhhdolggpgfmgahhilhbjgdhegkkeppiibnif","cldhiemiaoeaahi","cnjbbeedhddcjcelhcmfnmfcmpo","piekkbklhnpokeeiidbhbcjfgmddkejhdhfcenabobmjleemneikgkjokmcikiemgjhdmjcni","lknlpnlnnck","hgooknomoahjpebbcnidnagdfghooihkhjmeadhbojodhfkgkcbgmcbfkeammfhnlpbmoggfcgbcmfllalal","jljohilkaekocjcfdmpjgcpbpolfobdpmojcpmphlpnedaheholbbpdnnfoibpacmjmgglboodfoldicnjmodcipa","colgmamhgjeckgiiehibkipdl","afiljflhejeanlimdhcicfebapebcfefchdadfchfklcdgbkmagpkofpnphhhpbbpebkabdppocfbag"};
    	System.out.println(mp.maxProduct3(words));
    	System.out.println(mp.w1+":"+mp.w2);
    }
}
