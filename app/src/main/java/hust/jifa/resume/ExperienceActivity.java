package hust.jifa.resume;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jfxu on 16/10/3.
 */
public class ExperienceActivity extends Activity {

    RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        Experience e1 = new Experience("陶佳教", "武汉佳淘科技有限公司（创业）", "android开发", "2015/9", "2015/12",
                "和别人一起创业，想做一款针对大学生家教的产品，虽然成立了公司，但几经曲折最后失败了，人手不足是失败的主要原因\n" +
                        "当时我负责android这一块，公司成立的时候兼任管事");
        Experience e2 = new Experience("美玉秀秀", "南京美玉秀秀信息科技有限公司（实习）", "android开发实习生", "2016/2", "2016/5",
                "第一份实习工作，工作地点在武汉，一个创业型团队，做的是一款玉石交流交易平台，做的挺大的\n" +
                        "我的工作是修复一些 bug ，开发组件以及完善细节。\n" +
                        "这份工作一开始对于代码质量风格没有一个很明确的认识，通过这次实习，对于自己的代码质量要求有了极大的提升，对于和别人合作工作有了很大的进步，同时在处理问题的过程中对于android的了解更加深入");
        Experience e3 = new Experience("皇冠麻将", "百纳（武汉）信息技术有限公司（实习）", "游戏开发实习生", "2016/7", "至今",
                "学校安排的实习公司，由于没有android java 相关的实习项目，我选择了游戏开发\n" +
                        "技术栈用的是服务端 nodejs + 客户端 cocos creator\n" +
                        "我们项目组由四个实习生组成，公司希望推广全栈，于是有了这么个实验性项目，起初的目的是移植一个公司现有的麻将项目\n" +
                        "之后我们组做的还不错，公司也让制作人，策划，测试接入了这个项目，同时更改了项目目标，同时我们好像是所有实习生中唯一一个有奖金的组\n" +
                        "目前我在项目里算是主程，主要负责客户端游戏部分的编写，服务端有时候会去修复一些bug，同时负责和测试对接，项目的代码审核也是我在做，组内每个人的项目安排也是我在排\n" +
                        "这次实习经历是比较成功的一次，学到了很多东西，各种沟通技巧，项目上的大局观\n" +
                        "更多的一种成就感，公司认同我们做的东西，这让我觉得我做的事情是有意义的，确实是在为我所在的这个公司做出了贡献\n");
        Experience e4 = new Experience("各种小项目", "自己", "开发", "2013/9", "至今",
                "自己在学校也做过很多的小项目，比如日记，聊天，小说阅读器，天气应用，桌面宠物等，反正就是喜欢各种折腾");
        List<Experience> list = new ArrayList<>();
        list.add(e1);
        list.add(e2);
        list.add(e3);
        list.add(e4);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerAdapter adapter = new RecyclerAdapter(list);
        mRecyclerView.setAdapter(adapter);
    }
}
