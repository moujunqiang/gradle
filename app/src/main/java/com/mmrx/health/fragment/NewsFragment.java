package com.mmrx.health.fragment;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.fortysevendeg.swipelistview.SwipeListView;
import com.mmrx.health.R;
import com.mmrx.health.adapter.NewsAdapter;
import com.mmrx.health.bean.News;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment{

    SwipeListView slv = null;
    ListView lv = null;
	List<News> d_list;
	private TextView tv;
	int icon1Id = 0;
	int icon2Id = 0;
	int icon3Id = 0;
	int icon4Id = 0;
	int icon5Id = 0;
	int icon6Id = 0;
	int icon7Id = 0;
	int js1Id = 0;
	int js2Id = 0;
	int js3Id = 0;
	int js4Id = 0;
	int js5Id = 0;
	int js6Id = 0;
	int js7Id = 0;
	int js8Id = 0;
	int js9Id = 0;

	public NewsAdapter mAdapter = null;
	public static final String ACTION="com.by.wql.broatcast";
	public NewsFragment(){
		super();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragment_news, null);
	}
	@Override
	public void onDestroyView() {

		getActivity().unregisterReceiver(receiver);
		super.onDestroyView();
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {


		IntentFilter filter=new IntentFilter();
//		filter.addAction(ACTION);
		getActivity().registerReceiver(receiver, filter);
		init();
		super.onActivityCreated(savedInstanceState);
	}

	private void init() {
//        slv = (SwipeListView)getView().findViewById(R.id.news_swipe_list);
		lv = (ListView)getView().findViewById(R.id.news_swipe_list);
//		lv.addHeaderView(getView().findViewById(R.id.head_tv));
//		slv.setSwipeListViewListener(new SwipeListViewListener());
		tv = (TextView) getView().findViewById(R.id.news_tv);
		try {
			icon1Id = (Integer) R.drawable.class.getField("icon1").get(null);
			icon2Id = (Integer) R.drawable.class.getField("icon2").get(null);
			icon3Id = (Integer) R.drawable.class.getField("icon3").get(null);
			icon4Id = (Integer) R.drawable.class.getField("icon4").get(null);
			icon5Id = (Integer) R.drawable.class.getField("icon5").get(null);
			icon6Id = (Integer) R.drawable.class.getField("icon6").get(null);
			icon7Id = (Integer) R.drawable.class.getField("icon7").get(null);

			js1Id = (Integer) R.drawable.class.getField("js1").get(null);
			js2Id = (Integer) R.drawable.class.getField("js2").get(null);
			js3Id = (Integer) R.drawable.class.getField("js3").get(null);
			js4Id = (Integer) R.drawable.class.getField("js4").get(null);
			js5Id = (Integer) R.drawable.class.getField("js5").get(null);
			js6Id = (Integer) R.drawable.class.getField("js6").get(null);
			js7Id = (Integer) R.drawable.class.getField("js7").get(null);
			js8Id = (Integer) R.drawable.class.getField("js8").get(null);
			js9Id = (Integer) R.drawable.class.getField("js9").get(null);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}

		d_list=new ArrayList<News>();

		if (d_list==null || d_list.size() == 0) {
			d_list=new ArrayList<News>();
//			for (int i = 1; i <= 10; i++) {
				d_list.add(new News("一周食谱", "","",0));
				d_list.add(new News("周一", "芹菜二米粥","　材料：芹菜100克、大米100span>克、小米100克\n" +
						"　　做法：\n" +
						"　　1.将芹菜洗净，切成小段；大米、小米淘洗干净。\n" +
						"　　2.锅上火，加适量清水，放入大米、小米煮粥，先用旺火烧开后改用小火煮20分钟，加入芹菜段再煮5分钟即可。\n" +
						"　　瘦身效果：芹菜是低热量且富含粗纤维的超级瘦身食物，有助于肠道蠕动，促进肠道脂肪排泄，减少对食物中脂肪的吸收" +
						"，有防治肥胖的作用，同时，还能有效控制原发性高血压、高血脂的发生。\n",icon1Id));
			d_list.add(new News("周二", "番茄豆腐豆芽汤","材料：大红番茄1个(约100克)、北豆腐半盒(约100克)、豆芽菜50克、香菜少许、盐2小匙\n" +
					"　　做法：\n" +
					"　　1.将番茄洗净切块，豆腐成切小方块，豆芽菜去根，洗净，香菜洗净切段。\n" +
					"　　2.锅中放清水、豆腐块，开锅煮5分钟，再加番茄块、豆芽菜略煮，放盐调味，撒上香菜段即可。\n" +
					"　　瘦身效果：豆腐是我国的特产，能清热散血、和脾胃、消肿胀、下大肠浊气，是清肠瘦身的佳品。豆芽菜有利水消肿的功效。" +
					"番茄不仅能抗氧化、预防癌症，还是美容圣品。此汤低脂又美味，瘦身又养颜，不妨多多食用。\n",icon2Id));
			d_list.add(new News("周三", "双菇凉瓜丝","　原料：凉瓜(即苦瓜)150克，香菇100克，金针菇100克，酱油、姜、糖、香油适量。\n" +
					"　　做法：1.将凉瓜顺丝切成细丝，姜片切成细丝;2.香菇浸软切丝，金针菇切去尾端洗净;3.油爆姜丝后，加入凉瓜丝、冬菇丝及盐，同炒片刻;4.将金针菇加入同炒，加入调味料炒匀即可食用。\n" +
					"瘦身效果：香菇、金针菇能降低胆固醇;凉瓜富含纤维素，可减少脂肪吸收。\n",icon3Id));
			d_list.add(new News("周四", "玉米须菊花粥","材料：玉米须10克、菊花10克、大米200克、盐1小匙\n" +
					"　　做法：\n" +
					"　　1.将新鲜玉米须以温水略泡，冲洗干净。菊花去蒂，摘下花瓣，洗净。大米淘洗干净。\n" +
					"　　2.锅内倒入清水、玉米须，煮10分钟后滤去玉米须，加入大米煮至粥成，再放入盐、菊花、玉米须，略滚即成。\n" +
					"　　瘦身效果：玉米须性味甘、平，可利尿消肿、利胆退黄、降血压、降血糖、清热消脂，有人称其为“龙须”，正说明玉米须广泛使用的好处。干玉米须可在中药房买到。\n" +
					"　　菊花可疏散风热，平肝明目，清热解毒，降低血压。\n" +
					"取新鲜或干玉米须，同样搭配菊花，开水冲泡成玉米须菊花茶，其排水利尿、降脂减肥、排出体内毒素的效果也很好。\n",icon4Id));
			d_list.add(new News("周五", "熘鱼片","材料：草鱼取中段)1条、木耳10克、广东菜心50克、色拉油2大匙、盐1小匙、料酒1小匙，干淀粉、水淀粉、葱、姜各少许\n" +
					"　　做法：\n" +
					"　　1.将草鱼收拾干净，切片，沾裹干淀粉用温油滑熟;木耳泡发，洗净;广东菜心择洗干净;葱、姜切末。\n" +
					"　　2.锅中倒入色拉油烧热，放入葱、姜末爆香，再加入鱼片、木耳、菜心炒匀，加盐、料酒调味、倒水淀粉勾薄芡即可。\n" +
					"　　瘦身效果：草鱼肉质细嫩，高蛋白，低脂肪，低热量，富含有利于健康的不饱和脂肪酸，既美味可口，又能预防肥胖、美容抗衰老。" +
					"木耳是有益健康的黑色食，对于排除身体毒素功效一流。广东菜心富含粗纤维，清肠消脂。常食此菜既能补充营养，又让你无毒一身轻。\n",icon5Id));
			d_list.add(new News("周六", "芹菜炒墨鱼","材料：芹菜150克、墨鱼150克，葱花、红椒丝、黄椒丝各少许、色拉油1大匙、盐1小匙、鸡精1小匙\n" +
					"　　做法：\n" +
					"　　1.将芹菜择洗干净，切段;墨鱼清除内脏，洗净，切段后改花刀，用开水汆烫，沥干水分。\n" +
					"　　2.锅内倒入色拉油烧熟，放入葱花爆香，放入芹菜段翻炒几下，再放入墨鱼花、红椒丝、黄椒丝炒匀，最后加入盐、鸡精调味拌匀，装盘即可。\n" +
					"瘦身效果：芹菜可清理肠道、美容瘦身，墨鱼低脂肪、低热量，蛋白质含量很高，可以补充瘦身期间人体所需的营养，二者相配，多次也无妨。\n",icon6Id));
			d_list.add(new News("周日", "燕麦片粥","材料：燕麦片200克\n" +
					"　　做法：\n" +
					"　　锅上火，倒入适量水，放入燕麦片，烧开后用小火煮至麦片熟烂、浓稠即可。\n" +
					"　　瘦身效果：燕麦中所含的可溶性纤维是大米的12倍，是白面包的3倍，摄入的可溶性纤维能在人体大肠仲形成胶质令人体吸收食物养分的时间延长，较长时间地维持饱腹感，控制进食的欲望。还可降低胆固醇、降血糖、维持肠道健康，帮助节食者控制食欲。\n",icon7Id));


			/*健身*/
			d_list.add(new News("健身误区", "","",0));
			d_list.add(new News("误区一", "靠禁食减脂","有很多的减脂初学者，往往会选择通过禁食正餐来减脂，这是非常错误的选择。" +
					"我们人的身体每天都会有基础代谢，如果热量摄入少于基础代谢量，" +
					"那么身体就会自动认为我们不需要消耗那么多的热量，从而降低基础代谢量，" +
					"这样一来，不仅达不到减脂的效果，更多的情况下是吃以前同样的东西反而会变胖了。" +
					"所以，我们一定要按照自己的规划进餐。",js1Id));
			d_list.add(new News("误区二", "靠水果代餐","很多人想着，晚饭不吃，依靠水果代餐，这样就可以瘦，但其实是非常错误的。因为大部分水果中富含糖分，代餐水果势必会比平时的摄入多，一不小心就摄入了太多热量，达到反效果。我们的一餐应该由多种营养成分组成，减脂期更应该注意的是热量控制，例如将碳水换成粗粮类食品，多吃鸡肉，鱼肉等白肉作为蛋白质摄入，多吃蔬菜等等。",js2Id));
			d_list.add(new News("误区三", "迷信燃脂心率","有人的燃脂心率大概是110-130，很多了解了燃脂心率的运动者，往往会将心率控制在这个区间进行运动，却往往会忽视重要的东西：热量消耗。在燃脂心率下运动，热量消耗一半消耗脂肪，一半消耗糖原，确实是高效的燃脂方法，但是往往大家会忽视热量总量的问题，如果我们在燃脂心率下运动40分钟，与进行高心率运动40分钟，热量消耗差距非常巨大，这样算下来，燃烧的脂肪也差距很大了。所以我们进行燃脂心率运动同时，需要注意要延长运动的时间，这样才是合理的。",js3Id));
			d_list.add(new News("误区四", "不做无氧运动","许多运动者减脂时会选择有氧运动，这是正确的，有氧运动是减脂的主要运动手段，但很多运动者不进行无氧运动，这是不对的，遇到平台期的时候，我们需要加上一些无氧运动来帮助我们度过平台期。无氧运动同样可以消耗大量的热量，还能帮助我们增加肌肉含量，提高基础代谢，同样，换一种方式刺激身体，挑战身体，让身体不要适应你的变化，是减脂必须的一个环节。",js4Id));


			/*技巧*/
			d_list.add(new News("健身技巧", "","",0));
			d_list.add(new News("技巧一", "紧握哑铃，更准确来说是紧握双手","我们为什么要紧握双手呢？因为当我们紧握自己的双手时，我们就相当于给了我们自己一个支点。有句话叫做“给我一个支点，就能撬动地球”。而我们通过双手，可以启动我们身体上的很多稳定肌群，启动稳定肌群的意义就在于可以让你的上肢运动更加稳定，保证运动过程中的安全与动作的完成。",js5Id));
			d_list.add(new News("技巧二", "抬高你的胸骨","因为当我们要扩张胸部的活动范围时，抬头挺胸便是必须的。那么我们可以进行一波反向理解就是不能让自己陷入圆肩的状态，要完全伸展开来，这样身体才会有更好的伸展度。抬高胸骨还有另一个作用就是保持我们的肩胛骨处于一个正确位置。",js6Id));
			d_list.add(new News("技巧三", "拉——用手肘进行","在进行“拉”这个动作的时候，我们都要使用我们的手肘去驱动，最好的例子就是滑轮下拉这个动作，我们的双手在这个动作中扮演的角色仅仅是起到“钩子”的作用，在这个动作中，是手肘驱动我们的双手，用手肘进行引导。",js7Id));
			d_list.add(new News("技巧四", "保持臀部紧绷","保持臀部的紧绷，对于你在完成动作时，伸展你的髋关节是十分有帮助的。三大动作之一的硬拉，这个动作当你在完全伸展你的髋关节时，你可最大程度的保护你的腰。这就需要你意识到臀肌紧绷的重要性。",js8Id));
			d_list.add(new News("技巧五", "注意你的脚踝的位置","可能很少人会注意你的脚踝在运动中的位置，但每当你蹲下来，膝盖感觉痛的时候，你就应该意识到这个问题。脚踝的位置对于扁平足的小伙伴来说尤其需要注意。扁平足简单来讲就是你站立时脚与地面没有或者很少空隙——没有足弓。而扁平足很容易在你深蹲或者硬拉的时候导致你的胫骨内旋——造成足外翻。\n" +
					"那么我们要学会运用你的脚趾进行发力，脚趾的发力会让力分散到每一个脚趾，同时注意让你的脚踝向外，这样你会发觉你在深蹲，硬拉或者火箭推的时候，稳定性得到了很好的保证。\n",js9Id));

			/*lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
						System.out.println("第" + i + "行");
						Toast.makeText(getActivity(), "第" + i + "行", Toast.LENGTH_LONG).show();
					}
				});*/
//			}
		}

      /*  mAdapter=new NewsAdapter(d_list, getActivity(),slv,this);
        slv.setAdapter(mAdapter);*/
        mAdapter=new NewsAdapter(d_list, getActivity(),lv,this);
        lv.setAdapter(mAdapter);
	}
	

	private BroadcastReceiver receiver=new BroadcastReceiver() {

		@Override
		public void onReceive(Context arg0, Intent arg1) {
			// TODO Auto-generated method stub
		/*	d_list.add(new News("xt","测试数据"));
			d_list.add(new News("xt","测试数据"));
			d_list.add(new News("xt","测试数据"));
			d_list.add(new News("xt","测试数据"));
			d_list.add(new News("xt","测试数据"));
			d_list.add(new News("xt","测试数据"));*/
			if (mAdapter!=null) {
				mAdapter.setList(d_list);
			}
		}
	};

	public static Bitmap getLoacalBitmap(String url) {
		try {
			FileInputStream fis = new FileInputStream(url);
			return BitmapFactory.decodeStream(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

}
