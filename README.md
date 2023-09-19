# Assiginment_IMG

## 작동 방식

### 검색 방식

버튼을 클릭해도 되지만, 키보드 엔터를 쳐도 검색할 수 있습니다.

### 북마크 추가 방식

아이템의 LongClickListener를 사용
북마크 추가를 원한다면 LongClick을 하면 됩니다.




## 사용 gradle

    //glide
    implementation("com.github.bumptech.glide:glide:4.16.0")

    //retrofit2
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    //okhttp
    implementation("com.squareup.okhttp3:okhttp:4.10.0")

    //hash key를 받기 위함
    implementation ("com.kakao.sdk:v2-all:2.16.0")

    // viewmodel 및 lifecycle
    implementation("com.android.identity:identity-credential:20230420")
    val lifecycle_version = "2.5.1"
    val arch_version = "2.1.0"

    //activityViewmodel
    implementation("androidx.activity:activity-ktx:1.5.7")
    implementation("androidx.fragment:fragment-ktx:1.5.7")

    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
