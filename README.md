# Assiginment_IMG

### 작동 방식

#### 검색 방식

버튼을 클릭해도 되지만, 키보드 엔터를 쳐도 검색할 수 있습니다.

#### 북마크 작동 방식

아이템의 LongClickListener를 사용

 - 검색 결과에서 북마크 추가를 원한다면 LongClick을 하면 됩니다.
 - 북마크 삭제를 원한다면 역시 북마크에서 롱클릭을 하면 됩니다.

### 주요 기술

#### local.properties
local properties에 api키를 저장하고 ignore해줌으로써 api키를 숨겼습니다.

#### Retrofit & okhttp3

레트로핏의 서버간 통신을 좀 더 쉽게 도와주기 위해 okhttp3를 사용하였으며 header를 okhttp3로 만든 client에서 들고 있습니다.
따라서 retorfit의 interface에 header를 넣지 않았습니다.

#### ViewModel & ListAdapter

ListAdpater를 사용함으로써 이전 recyclearview.adapter의 불편한 사용을 전부 상쇄하였습니다.
주입받는 리스트에 반응하여 자동으로 최소한의 복잡도로 뷰를 그릴 수 있습니다.

뷰 모델을 사용하여 fragment와 data를 분리하였습니다.
따라서 fragment에서 직접적으로 데이터를 다루지 않고 오직 view만 다룰 수 있게 되었습니다.

또한 sharedviewmodel을 사용하여 각 fragment간의 정보전달이 무척이나 단순해졌습니다.

#### factory 패턴

factory 패턴을 사용하게 되어 뷰 모델이 context를 간접적으로 전달받아 사용하게 됩니다.
따라서 뷰와 데이터의 완전한 분리가 유지되었습니다.
또한 repository역시 factory패턴을 사용함으로서 viewmodel과 최대한 분리하였습니다.

#### repository

repository를 이용하여 retrofit으로 받아오는 api를 처리하도록 하였습니다.
그 결과로 viewmodel의 data layer층을 분리하게 되었으며
퍼사드 패턴/repositorty 패턴/브릿지 패턴 을 사용하여 분리되었다고는 하는데 이에 대한 이해도는 아직 떨어지는 상태입니다.



## 사용 gradle

    //gson
    implementation("com.google.code.gson:gson:2.10.1")

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
