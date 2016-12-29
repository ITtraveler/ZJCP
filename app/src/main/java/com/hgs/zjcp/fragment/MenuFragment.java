package com.hgs.zjcp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hgs.zjcp.ui.MenuListActivity;
import com.hgs.zjcp.R;
import com.hgs.zjcp.adapter.MyGridAdapter;
import com.hgs.zjcp.data.MobMenuCategory;
import com.hgs.zjcp.utils.net.NetAction;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/10/26.
 */
public class MenuFragment extends BaseFragment {
    private static MenuFragment menuFragment;
    //数据
    private List<MobMenuCategory> menuCategoryList;

    public static MenuFragment newInstance() {
        if (menuFragment == null) {
            menuFragment = new MenuFragment();
        }
        return menuFragment;
    }


    @BindView(R.id.menu_cate_layout)
    LinearLayout cateLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        menuCategoryList = NetAction.newAction().getMenuCategoryList(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        ButterKnife.bind(this, view);
        initMenu();
        return view;
    }

    //初始化菜单
    private void initMenu() {
        final boolean[] isShow = {false, false, false, false, false};
        for (int i = 0; i < menuCategoryList.size(); i++) {
//        for (MobMenuCategory menuCategory : menuCategoryList) {
            final MobMenuCategory.Child[] children = menuCategoryList.get(i).getChilds();
            final View cateView = LayoutInflater.from(getContext()).inflate(R.layout.menu_category_content, null);
            TextView tvMenuName = (TextView) cateView.findViewById(R.id.menu_name);
            tvMenuName.setText(menuCategoryList.get(i).getName());
            final GridView gridView = (GridView) cateView.findViewById(R.id.menu_gridView);
            gridView.setAdapter(new MyGridAdapter(getContext(), children));
            //gridView的item点击事件
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //打开菜谱分类的菜list
                    Intent intent = new Intent(getContext(), MenuListActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("category", children[position]);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    System.out.println(children[position]);
                }
            });
            final ImageView imageView = (ImageView) cateView.findViewById(R.id.menu_ic_push);
            View viewBar = cateView.findViewById(R.id.menu_bar);
            final int finalI = i;
            //类别条的点的 事件，点击打开GridView
            viewBar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isShow[finalI]) {
                        gridView.setVisibility(View.GONE);
                        isShow[finalI] = false;
                        cateLayout.invalidate();
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_push_more));
                    } else {
                        gridView.setVisibility(View.VISIBLE);
                        isShow[finalI] = true;
                        cateLayout.invalidate();
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_push_no_more));
                    }
                }
            });

            cateLayout.addView(cateView);

        }
    }


}
//private MyRecycleAdapter mRecycleAdapter;
//@BindView(R.id.menu_recycleView)
//RecyclerView mRecycleView;


//        mRecycleAdapter = new MyRecycleAdapter(menuCategoryList);
//        mRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
//        mRecycleView.setAdapter(mRecycleAdapter);


//
//    class MyRecycleAdapter extends RecyclerView.Adapter<MyAdapterHolder> {
//        List<MobMenuCategory> menuCategoryList;
//
//        MyRecycleAdapter(List<MobMenuCategory> menuCategoryList) {
//            this.menuCategoryList = menuCategoryList;
//        }
//
//        @Override
//        public MyAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(getContext()).inflate(R.layout.menu_category_content, null);
//            return new MyAdapterHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(MyAdapterHolder holder, int position) {
//            MobMenuCategory menu = menuCategoryList.get(position);
//            holder.tvMenuName.setText("" + menu.getName());
//            MobMenuCategory.Child[] mChildren = menu.getChilds();
//            holder.gridView.setAdapter(new MyGridAdapter(getContext(), mChildren));
//
//        }
//
//        @Override
//        public int getItemCount() {
//            return menuCategoryList.size();
//        }
//
//    }
//
//    class MyAdapterHolder extends RecyclerView.ViewHolder {
//        @BindView(R.id.menu_name)
//        TextView tvMenuName;
//        @BindView(R.id.menu_gridView)
//        GridView gridView;
//
//        public MyAdapterHolder(View itemView) {
//            super(itemView);
//            ButterKnife.bind(this, itemView);
//        }
//
//    }