# 前言

最近在使用网易云音乐的时候，看到如下图的排版效果图，自己也想实现一个

![这里写图片描述](http://img.blog.csdn.net/20171029200421231?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXFfMzAzNzk2ODk=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

这里采用网上用法最多的方式，而且是比较简单的方式实现的，想要做项目的同学也可以快速入手搞定首页界面，可以在最快的时间内模仿出来，且效果达到90%以上的相似

# 效果演示

至于图片的加载你们可以根据网上的Api获取相应的图片加载到对应的位置，这里只是采用本地图片来演示

![这里写图片描述](http://img.blog.csdn.net/20171029195831712?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXFfMzAzNzk2ODk=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

# 实现分析

这里是采用RecyclerView的GridLayoutManager的一个SpanSize这么一个东西，从下图很容易知道其意思

![这里写图片描述](http://img.blog.csdn.net/20171029200803446?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXFfMzAzNzk2ODk=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

# 项目结构

项目结构可能对初学者感觉很庞大，不用担心，这里我会按照下面的包名划分，从最简单的开始分析

![这里写图片描述](http://img.blog.csdn.net/20171029201600501?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXFfMzAzNzk2ODk=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

## 引入依赖

首先是在Gradle中引入对RecyclerView的依赖

```
compile 'com.android.support:recyclerview-v7:25.3.1'
```

## View包

由于项目用到的图片是有规格限定的，所以需要对ImageView覆写，得到我们想要尺寸的图片

* SquareImageView：正方形图片
* RectImageView：长方形图片

```
public class SquareImageView extends android.support.v7.widget.AppCompatImageView {

    public SquareImageView(Context context) {
        this(context,null);
    }

    public SquareImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SquareImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setScaleType(ScaleType.FIT_XY);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
```

```
public class RectImageView extends android.support.v7.widget.AppCompatImageView {

    public RectImageView(Context context) {
        this(context, null);
    }

    public RectImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RectImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setScaleType(ScaleType.FIT_XY);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        // 设置大小为宽度的三分之二
        int halfWidthMeasureSpec = MeasureSpec.makeMeasureSpec(width / 3 * 2, widthMode);
        super.onMeasure(widthMeasureSpec, halfWidthMeasureSpec);
    }
}
```

## Music包

这里是我们存储实体的地方，其中四种类型的划分，分别对应项目展示中的前三个模块的划分，其中还有一个标题也算是一种类型，所以共四种

```
public class Music {

    public int type;
    public String title;
    // 后期可加入Glide加载网络图片Url
    public int imageId;

    public interface TYPE {
        int TYPE_GRID_THREE = 0x01;
        int TYPE_GRID_TWO = 0x02;
        int TYPE_LIST = 0x03;
        int TYPE_TITLE = 0x04;
    }
}
```

## Listener包

由于RecyclerView自身是没有点击事件的，所以这个包是RecyclerView的点击事件接口

```
public interface OnItemClickListener {
    void OnItemClick(int position);
}
```

## Decoration包

由于RecyclerView是不提供分割线的，所以这个包是自定义的分割线

```
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public SpacesItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = space;
        outRect.right = space;
        outRect.bottom = space;
        outRect.top = space;
    }
}
```

## ViewHolder

这里存储的是我们混排效果的控件，标题可能会有点区别，其他是一样的效果，为了后期方便拓展，我们就把他们分开，不代码复用

```
public class GridThreeViewHolder extends RecyclerView.ViewHolder {

    public SquareImageView iv_icon;
    public TextView tv_content;

    public GridThreeViewHolder(View itemView) {
        super(itemView);
        iv_icon = (SquareImageView) itemView.findViewById(R.id.iv_icon);
        tv_content = (TextView) itemView.findViewById(R.id.tv_content);
    }
}
```

```
public class GridTwoViewHolder extends RecyclerView.ViewHolder {

    public RectImageView iv_icon;
    public TextView tv_content;

    public GridTwoViewHolder(View itemView) {
        super(itemView);
        iv_icon = (RectImageView) itemView.findViewById(R.id.iv_icon);
        tv_content = (TextView) itemView.findViewById(R.id.tv_content);
    }
}
```

```
public class ListViewHolder extends RecyclerView.ViewHolder {

    public RectImageView iv_icon;
    public TextView tv_content;

    public ListViewHolder(View itemView) {
        super(itemView);
        iv_icon = (RectImageView) itemView.findViewById(R.id.iv_icon);
        tv_content = (TextView) itemView.findViewById(R.id.tv_content);
    }
}
```

```
public class TitleViewHolder extends RecyclerView.ViewHolder {

    public TextView tv_content;

    public TitleViewHolder(View itemView) {
        super(itemView);
        tv_content = (TextView) itemView.findViewById(R.id.tv_content);
    }
}
```

## Adapter包

这里就是对所有ViewHolder的控制器，然而这里并不是混排效果实现的最终地方，只不过是填充数据的地方

```
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private List<Music> mList;
    private Context mContext;
    private LayoutInflater mInflater;

    /**
     * 点击事件
     */
    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public RecyclerAdapter(Context context, List<Music> list) {
        this.mList = list;
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder mViewHolder = null;
        if (viewType == Music.TYPE.TYPE_GRID_THREE) {
            view = mInflater.inflate(R.layout.item_grid_three, parent, false);
            mViewHolder = new GridThreeViewHolder(view);
        } else if (viewType == Music.TYPE.TYPE_GRID_TWO) {
            view = mInflater.inflate(R.layout.item_grid_two, parent, false);
            mViewHolder = new GridTwoViewHolder(view);
        } else if (viewType == Music.TYPE.TYPE_LIST) {
            view = mInflater.inflate(R.layout.item_list, parent, false);
            mViewHolder = new ListViewHolder(view);
        } else if (viewType == Music.TYPE.TYPE_TITLE) {
            view = mInflater.inflate(R.layout.item_title, parent, false);
            mViewHolder = new TitleViewHolder(view);
        }
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case Music.TYPE.TYPE_GRID_THREE:
                GridThreeViewHolder gHolder_three = (GridThreeViewHolder) holder;
                gHolder_three.tv_content.setText(mList.get(position).title);
                gHolder_three.iv_icon.setImageResource(mList.get(position).imageId);
                //点击事件
                gHolder_three.itemView.setOnClickListener(this);
                gHolder_three.itemView.setTag(position);
                break;
            case Music.TYPE.TYPE_GRID_TWO:
                GridTwoViewHolder gHolder_two = (GridTwoViewHolder) holder;
                gHolder_two.tv_content.setText(mList.get(position).title);
                gHolder_two.iv_icon.setImageResource(mList.get(position).imageId);
                //点击事件
                gHolder_two.itemView.setOnClickListener(this);
                gHolder_two.itemView.setTag(position);
                break;
            case Music.TYPE.TYPE_LIST:
                ListViewHolder lHolder = (ListViewHolder) holder;
                lHolder.tv_content.setText(mList.get(position).title);
                lHolder.iv_icon.setImageResource(mList.get(position).imageId);
                //点击事件
                lHolder.itemView.setOnClickListener(this);
                lHolder.itemView.setTag(position);
                break;
            case Music.TYPE.TYPE_TITLE:
                TitleViewHolder tHolder = (TitleViewHolder) holder;
                tHolder.tv_content.setText(mList.get(position).title);
                //点击事件
                tHolder.itemView.setOnClickListener(this);
                tHolder.itemView.setTag(position);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).type;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            int position = (int) v.getTag();
            mOnItemClickListener.OnItemClick(position);
        }
    }
}
```

## Activity

这里就是我们实现混排效果的关键，我们会根据不同类型的数据，对RecyclerView的SpanSize的进行设置

```
public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    private RecyclerView ry;
    private GridLayoutManager layoutManager;
    private RecyclerAdapter mAdapter;
    private static List<Music> mList;

    /**
     * 模拟本地数据
     */
    static {
        mList = new ArrayList<>();

        for (int i = 0; i < 1; i++) {
            Music music = new Music();
            music.type = Music.TYPE.TYPE_TITLE;
            music.imageId = R.drawable.ic_cover;
            music.title = "推荐歌单";
            mList.add(music);
        }

        for (int i = 0; i < 6; i++) {
            Music music = new Music();
            music.type = Music.TYPE.TYPE_GRID_THREE;
            music.imageId = R.drawable.ic_cover;
            music.title = "先不要降温！我没钱买衣服";
            mList.add(music);
        }

        for (int i = 0; i < 1; i++) {
            Music music = new Music();
            music.type = Music.TYPE.TYPE_TITLE;
            music.imageId = R.drawable.ic_cover;
            music.title = "推荐MV";
            mList.add(music);
        }

        for (int i = 0; i < 4; i++) {
            Music music = new Music();
            music.type = Music.TYPE.TYPE_GRID_TWO;
            music.imageId = R.drawable.ic_cover;
            music.title = "Perfect Day";
            mList.add(music);
        }

        for (int i = 0; i < 1; i++) {
            Music music = new Music();
            music.type = Music.TYPE.TYPE_TITLE;
            music.imageId = R.drawable.ic_cover;
            music.title = "精选专栏";
            mList.add(music);
        }

        for (int i = 0; i < 3; i++) {
            Music music = new Music();
            music.type = Music.TYPE.TYPE_LIST;
            music.imageId = R.drawable.ic_cover;
            music.title = "去看《银翼杀手2049》前，你应该知道的三件事";
            mList.add(music);
        }

        for (int i = 0; i < 1; i++) {
            Music music = new Music();
            music.type = Music.TYPE.TYPE_TITLE;
            music.imageId = R.drawable.ic_cover;
            music.title = "最新音乐";
            mList.add(music);
        }

        for (int i = 0; i < 6; i++) {
            Music music = new Music();
            music.type = Music.TYPE.TYPE_GRID_THREE;
            music.imageId = R.drawable.ic_cover;
            music.title = "[BGM]一定听过的神级背景配乐";
            mList.add(music);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ry = (RecyclerView) findViewById(R.id.ry);
        layoutManager = new GridLayoutManager(this, 6);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                int type = mList.get(position).type;
                if (type == Music.TYPE.TYPE_GRID_THREE) {
                    return 2;
                } else if (type == Music.TYPE.TYPE_GRID_TWO) {
                    return 3;
                } else if (type == Music.TYPE.TYPE_LIST) {
                    return 6;
                } else if (type == Music.TYPE.TYPE_TITLE) {
                    return 6;
                }
                return 0;
            }
        });
        ry.setLayoutManager(layoutManager);
        ry.addItemDecoration(new SpacesItemDecoration(2));

        // 填充数据
        mAdapter = new RecyclerAdapter(this, mList);
        mAdapter.setOnItemClickListener(this);
        ry.setAdapter(mAdapter);

    }

    @Override
    public void OnItemClick(int position) {
        String title = mList.get(position).title;
        Toast.makeText(this, title, Toast.LENGTH_SHORT).show();
    }
}
```

## layout布局文件

这里的布局很简单，比如用到我们的正方形图片，长方形图片等，这里就不做代码贴出，详细可以查看源码

## 源码下载

[源码下载](https://github.com/AndroidHensen/RecyclerPage)