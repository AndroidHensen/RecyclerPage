# ǰ��

�����ʹ�����������ֵ�ʱ�򣬿�������ͼ���Ű�Ч��ͼ���Լ�Ҳ��ʵ��һ��

![����дͼƬ����](http://img.blog.csdn.net/20171029200421231?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXFfMzAzNzk2ODk=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

������������÷����ķ�ʽ�������ǱȽϼ򵥵ķ�ʽʵ�ֵģ���Ҫ����Ŀ��ͬѧҲ���Կ������ָ㶨��ҳ���棬����������ʱ����ģ�³�������Ч���ﵽ90%���ϵ�����

# Ч����ʾ

����ͼƬ�ļ������ǿ��Ը������ϵ�Api��ȡ��Ӧ��ͼƬ���ص���Ӧ��λ�ã�����ֻ�ǲ��ñ���ͼƬ����ʾ

![����дͼƬ����](http://img.blog.csdn.net/20171029195831712?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXFfMzAzNzk2ODk=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

# ʵ�ַ���

�����ǲ���RecyclerView��GridLayoutManager��һ��SpanSize��ôһ������������ͼ������֪������˼

![����дͼƬ����](http://img.blog.csdn.net/20171029200803446?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXFfMzAzNzk2ODk=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

# ��Ŀ�ṹ

��Ŀ�ṹ���ܶԳ�ѧ�߸о����Ӵ󣬲��õ��ģ������һᰴ������İ������֣�����򵥵Ŀ�ʼ����

![����дͼƬ����](http://img.blog.csdn.net/20171029201600501?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXFfMzAzNzk2ODk=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

## ��������

��������Gradle�������RecyclerView������

```
compile 'com.android.support:recyclerview-v7:25.3.1'
```

## View��

������Ŀ�õ���ͼƬ���й���޶��ģ�������Ҫ��ImageView��д���õ�������Ҫ�ߴ��ͼƬ

* SquareImageView��������ͼƬ
* RectImageView��������ͼƬ

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
        // ���ô�СΪ��ȵ�����֮��
        int halfWidthMeasureSpec = MeasureSpec.makeMeasureSpec(width / 3 * 2, widthMode);
        super.onMeasure(widthMeasureSpec, halfWidthMeasureSpec);
    }
}
```

## Music��

���������Ǵ洢ʵ��ĵط��������������͵Ļ��֣��ֱ��Ӧ��Ŀչʾ�е�ǰ����ģ��Ļ��֣����л���һ������Ҳ����һ�����ͣ����Թ�����

```
public class Music {

    public int type;
    public String title;
    // ���ڿɼ���Glide��������ͼƬUrl
    public int imageId;

    public interface TYPE {
        int TYPE_GRID_THREE = 0x01;
        int TYPE_GRID_TWO = 0x02;
        int TYPE_LIST = 0x03;
        int TYPE_TITLE = 0x04;
    }
}
```

## Listener��

����RecyclerView������û�е���¼��ģ������������RecyclerView�ĵ���¼��ӿ�

```
public interface OnItemClickListener {
    void OnItemClick(int position);
}
```

## Decoration��

����RecyclerView�ǲ��ṩ�ָ��ߵģ�������������Զ���ķָ���

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

����洢�������ǻ���Ч���Ŀؼ���������ܻ��е�����������һ����Ч����Ϊ�˺��ڷ�����չ�����ǾͰ����Ƿֿ��������븴��

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

## Adapter��

������Ƕ�����ViewHolder�Ŀ�������Ȼ�����ﲢ���ǻ���Ч��ʵ�ֵ����յط���ֻ������������ݵĵط�

```
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private List<Music> mList;
    private Context mContext;
    private LayoutInflater mInflater;

    /**
     * ����¼�
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
                //����¼�
                gHolder_three.itemView.setOnClickListener(this);
                gHolder_three.itemView.setTag(position);
                break;
            case Music.TYPE.TYPE_GRID_TWO:
                GridTwoViewHolder gHolder_two = (GridTwoViewHolder) holder;
                gHolder_two.tv_content.setText(mList.get(position).title);
                gHolder_two.iv_icon.setImageResource(mList.get(position).imageId);
                //����¼�
                gHolder_two.itemView.setOnClickListener(this);
                gHolder_two.itemView.setTag(position);
                break;
            case Music.TYPE.TYPE_LIST:
                ListViewHolder lHolder = (ListViewHolder) holder;
                lHolder.tv_content.setText(mList.get(position).title);
                lHolder.iv_icon.setImageResource(mList.get(position).imageId);
                //����¼�
                lHolder.itemView.setOnClickListener(this);
                lHolder.itemView.setTag(position);
                break;
            case Music.TYPE.TYPE_TITLE:
                TitleViewHolder tHolder = (TitleViewHolder) holder;
                tHolder.tv_content.setText(mList.get(position).title);
                //����¼�
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

�����������ʵ�ֻ���Ч���Ĺؼ������ǻ���ݲ�ͬ���͵����ݣ���RecyclerView��SpanSize�Ľ�������

```
public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    private RecyclerView ry;
    private GridLayoutManager layoutManager;
    private RecyclerAdapter mAdapter;
    private static List<Music> mList;

    /**
     * ģ�Ȿ������
     */
    static {
        mList = new ArrayList<>();

        for (int i = 0; i < 1; i++) {
            Music music = new Music();
            music.type = Music.TYPE.TYPE_TITLE;
            music.imageId = R.drawable.ic_cover;
            music.title = "�Ƽ��赥";
            mList.add(music);
        }

        for (int i = 0; i < 6; i++) {
            Music music = new Music();
            music.type = Music.TYPE.TYPE_GRID_THREE;
            music.imageId = R.drawable.ic_cover;
            music.title = "�Ȳ�Ҫ���£���ûǮ���·�";
            mList.add(music);
        }

        for (int i = 0; i < 1; i++) {
            Music music = new Music();
            music.type = Music.TYPE.TYPE_TITLE;
            music.imageId = R.drawable.ic_cover;
            music.title = "�Ƽ�MV";
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
            music.title = "��ѡר��";
            mList.add(music);
        }

        for (int i = 0; i < 3; i++) {
            Music music = new Music();
            music.type = Music.TYPE.TYPE_LIST;
            music.imageId = R.drawable.ic_cover;
            music.title = "ȥ��������ɱ��2049��ǰ����Ӧ��֪����������";
            mList.add(music);
        }

        for (int i = 0; i < 1; i++) {
            Music music = new Music();
            music.type = Music.TYPE.TYPE_TITLE;
            music.imageId = R.drawable.ic_cover;
            music.title = "��������";
            mList.add(music);
        }

        for (int i = 0; i < 6; i++) {
            Music music = new Music();
            music.type = Music.TYPE.TYPE_GRID_THREE;
            music.imageId = R.drawable.ic_cover;
            music.title = "[BGM]һ���������񼶱�������";
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

        // �������
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

## layout�����ļ�

����Ĳ��ֺܼ򵥣������õ����ǵ�������ͼƬ��������ͼƬ�ȣ�����Ͳ���������������ϸ���Բ鿴Դ��

## Դ������

[Դ������](https://github.com/AndroidHensen/RecyclerPage)