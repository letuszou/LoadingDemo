## 思路 ##

替代RecyclerView的列表显示的位置

## 使用方法 ##

第一步：导入项目依赖


第二步：布局文件中导入

     <include layout="@layout/load_utils"/>

第三步：java代码导入<br>


       LoadUtils loadUtils = new LoadUtils(this,recycler_list,recyclerAdapter) {
            @Override
            public void onRefresh() {
                //点击空布局刷新的方法
            }
        };`


第四步：在网络数据显示的地方和网络错误的地方调用


         loadUtils.viewFinish();

