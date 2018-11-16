    DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);

  @OnItemClick({R.id.plv})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
    }