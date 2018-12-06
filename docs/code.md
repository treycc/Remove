    DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);

  @OnItemClick({R.id.plv})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
    }


  memberSelectPresenter.getEmployeeByCompany(new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("ProjectId", projectId)
                .addFormDataPart("CompanyTypeId", String.valueOf(companyTypeId))
                .addFormDataPart("PageSize", String.valueOf(Constants.PAGE_SIZE_100))
                .addFormDataPart("PageIndex", String.valueOf(Constants.PAGE_FIRST))
                .addFormDataPart("KeyWords", keyword)
                .build());