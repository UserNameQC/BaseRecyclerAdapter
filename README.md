# BaseRecyclerAdapter 
## RecyclerView简单的适配器，适用于普通列表，目前不具备分组等其他功能。
## 更改兼容Kotlin
## 如何使用：
## 1.在项目目录下build.gradle中repositories下添加：
    maven { url 'https://jitpack.io' }；
    最新AndroidStudio 版本是添加在setting.gradle 中。
## 2.在使用的Module的build.gradle 中添加：
    implementation 'com.github.UserNameQC:BaseRecyclerAdapter:1.1'
## 3.新建adpter类，继承BaseRecyclerAdapter<此处为item实体类>,重写Cover方法；并添加类构造方法。
## 4.此库有使用Binding绑定视图。在build.gradle 中开启Binding

    android {
        .....

        buildFeatures {
            dataBinding true
        }
    }
## 5.创建Adapter类，继承BaseRecyclerAdapter<T>,泛型为数据模型；创建构造方法，传入Item的View；实现convert方法。
 ### 示例如下：

    public class WarehousAdapter extends BaseRecyclerAdapter<MaterialsModel> {

        public WarehousAdapter(Context mContext, ObservableArrayList<MaterialsModel> mDatas) {
            super(mContext, mDatas, R.layout.item_material_layout);
        }

        @Override
        public void convert(Context mContext, BaseViewHolder holder, MaterialsModel materialsModel, int position) {
            holder.binding.setVariable(BR.material, materialsModel);
            /**关于item的所有操作都在此方法中实现*/
        }
    }

### 示例 MaterialsModel实体类：

    public class MaterialsModel {
        private String title;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
## 6.使用
### 项目中使用ObservableArrayList<T> 作为数据的集合类型
    private ObservableArrayList<TestModel> list = new ObservableArrayList<>();

### Adapter适配器初始化 

    for (int i = 0; i < 5; i++) {
        TestModel testModel = new TestModel();
        testModel.setName("测试" + i);
        list.add(testModel);
    }

    binding.recycler.setLayoutManager(new LinearLayoutManager(this));
    TestAdapter testAdapter = new TestAdapter(this, list);
    binding.recycler.setAdapter(testAdapter);

### item监听事件：
    
    warehousAdapter.setOnItemClickListener(((view, integer) -> {
            Toast.makeText(this, "点击了" + list.get(integer), Toast.LENGTH_SHORT).show();
            return null;
        }));
### item长按监听事件：

    warehousAdapter.testAdapter.setonLongItemClickListener((view, position) ->{
            Toast.makeText(this, "点击了" + list.get(integer), Toast.LENGTH_SHORT).show();
            return null;
        });

