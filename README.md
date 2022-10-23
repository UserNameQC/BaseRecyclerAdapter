# BaseRecyclerAdapter 
## RecyclerView简单的适配器，适用于普通列表，目前不具备分组等其他功能。
## 如何使用：
## 1.在项目目录下build.gradle中repositories下添加：
    maven { url 'https://jitpack.io' }；
    最新AndroidStudio 版本是添加在setting.gradle 中。
## 2.在使用的Module的build.gradle 中添加：
    implementation 'com.github.UserNameQC:BaseRecyclerAdapter:1.1'
## 3.新建adpter类，继承BaseRecyclerAdapter<此处为item实体类>,重写Cover方法；并添加类构造方法。
## 4.此库有使用Binding绑定视图。
## 示例：


    public class WarehousAdapter extends BaseRecyclerAdapter<MaterialsModel> {

        public WarehousAdapter(Context mContext, ObservableArrayList<MaterialsModel> mDatas) {
            super(mContext, mDatas, R.layout.item_material_layout);
        }

        @Override
        public void convert(Context mContext, BaseViewHolder holder, MaterialsModel materialsModel, int position) {
            holder.binding.setVariable(BR.material, materialsModel);
        }
    }

## MaterialsModel实体类：

    public class MaterialsModel {
        private String title;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
## item监听时间：

    warehousAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {

                }
            });
## item长按监听时间：

    warehousAdapter.setonLongItemClickListener(new BaseRecyclerAdapter.onLongItemClickListener() {
               @Override
               public void onLongItemClick(View view, int postion) {

               }
           });

