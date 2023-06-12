let EncodingKey =
  "TZbDo7Yq6ZOsgKZCMNWkFpsbw1HUNdOPrBDbRSRam6PmT%2BNay0fzAWkyoxtAv1pGDEqrynpcT4qQE6ss0QEhYA%3D%3D";
let DecodingKey =
  "TZbDo7Yq6ZOsgKZCMNWkFpsbw1HUNdOPrBDbRSRam6PmT+Nay0fzAWkyoxtAv1pGDEqrynpcT4qQE6ss0QEhYA==";

let app = document.querySelector("#app");
let responseText,
  items,
  text = "";
let markers = [];
let ctprvnNm = null;
let signguNm = null;
let Interval = null;
const now = new Date();
const date = new Date(now.getFullYear(), now.getMonth(), now.getDate());

const elapsedMSec = now.getTime() - date.getTime(); // 9004000
const elapsedSec = elapsedMSec / 1000; // 9004

// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

var map = new kakao.maps.Map(document.getElementById("map"), {
  //지도를 생성할 때 필요한 기본 옵션
  center: new kakao.maps.LatLng(33.450701, 126.570667), //지도의 중심좌표.
  level: 1, //지도의 레벨(확대, 축소 정도)
}); //지도 생성 및 객체 리턴

function getLocation() {
  //gps
  if (navigator.geolocation) {
    // GPS를 지원하면
    navigator.geolocation.getCurrentPosition(
      function (position) {
        panTo(position.coords.latitude, position.coords.longitude);
      },
      function (error) {
        console.error(error);
      },
      {
        enableHighAccuracy: false,
        maximumAge: 0,
        timeout: Infinity,
      }
    );
  } else {
    alert("GPS를 지원하지 않습니다");
  }
}
getLocation();

{
  //지도 제어
  // 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
  var zoomControl = new kakao.maps.ZoomControl();
  map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

  // 지도가 확대 또는 축소되면 마지막 파라미터로 넘어온 함수를 호출하도록 이벤트를 등록합니다
  kakao.maps.event.addListener(map, "zoom_changed", function () {
    var center = map.getCenter();
    // 지도의 현재 레벨을 얻어옵니다
    var level = map.getLevel();
    if (level <= 9) {
      showMarkers();
      searchAddrFromCoords(map.getCenter(), displayCenterInfo);
    } else {
      hideMarkers();
    }
  });

  // 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
  kakao.maps.event.addListener(map, "idle", function () {
    searchAddrFromCoords(map.getCenter(), displayCenterInfo);
  });

  function panTo(lat, lng) {
    //moveMap
    // 이동할 위도 경도 위치를 생성합니다
    var moveLatLon = new kakao.maps.LatLng(lat, lng);

    // 지도 중심을 부드럽게 이동시킵니다
    map.panTo(moveLatLon);
  }
}
{
  //마커 관련 함수
  function pinpoint(title, lat, lng, sgnaspOrdr, sgnaspTime, sgnaspYn) {
    console.log(sgnaspOrdr + " " + sgnaspTime + " " + sgnaspYn);
    // 마커 이미지의 이미지 주소입니다
    var imageSrc =
      "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

    var imageSize = new kakao.maps.Size(24, 35);

    // 마커 이미지를 생성합니다
    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
    var point = new kakao.maps.LatLng(lat, lng);

    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({
      map: map, // 마커를 표시할 지도
      position: point, // 마커를 표시할 위치
      title: title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
      image: markerImage, // 마커 이미지
      clickable: true,
    });

    var infowindow = new kakao.maps.InfoWindow({
      content: title, // 인포윈도우에 표시할 내용
      removable: true,
    });

    // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
    // 이벤트 리스너로는 클로저를 만들어 등록합니다
    // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
    kakao.maps.event.addListener(
      marker,
      "click",
      makeOverListener(
        map,
        marker,
        infowindow,
        sgnaspOrdr,
        sgnaspTime,
        sgnaspYn
      )
    );
    kakao.maps.event.addListener(
      marker,
      "mouseout",
      makeOutListener(infowindow)
    );

    markers.push(marker);
  }

  function makeOverListener(
    map,
    marker,
    infowindow,
    sgnaspOrdr,
    sgnaspTime,
    sgnaspYn
  ) {
    var sgnaspOrdrs = sgnaspOrdr.split("+");
    var sgnaspTimes = sgnaspTime.split("+");
    var num2 = 0;
    for (var i = 0; i < sgnaspTimes.length; i++) {
      num2 += parseInt(sgnaspTimes[i]);
    }

    return function () {
      Interval = setInterval(() => {
        infowindow.setContent(
          sgnaspOrdrs[0] +
            " " +
            ((elapsedSec % num2) - sgnaspTimes[0]) +
            "초 남음"
        );
        console.log(infowindow.getContent);
      }, 1000);
      infowindow.open(map, marker);
    };
  }

  // 인포윈도우를 닫는 클로저를 만드는 함수입니다
  function makeOutListener(infowindow) {
    return function () {
      clearInterval(Interval);
      infowindow.close();
    };
  }

  function setMarkers(map) {
    //마커를 지도에 표시하는 함수

    for (var i = 0; i < markers.length; i++) {
      markers[i].setMap(map);
    }
  }
  function showMarkers() {
    //마커 표시
    setMarkers(map);
  }

  function hideMarkers() {
    //마커 제거
    setMarkers(null);
  }
}
{
  function searchAddrFromCoords(coords, callback) {
    // 좌표로 행정동 주소 정보를 요청합니다
    geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);
  }

  // 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
  function displayCenterInfo(result, status) {
    if (status === kakao.maps.services.Status.OK) {
      for (var i = 0; i < result.length; i++) {
        // 행정동의 region_type 값은 'H' 이므로
        if (result[i].region_type === "H") {
          ctprvnNm = result[i].region_1depth_name;
          signguNm = result[i].region_2depth_name;
          break;
        }
      }
    }
  }
}
function sendRequest() {
  //공공데이터 포털에서 데이터 받아오는 코드
  text = "";
  app.innerText = text;
  var xhr = new XMLHttpRequest();
  var url =
    "http://api.data.go.kr/openapi/tn_pubr_public_traffic_light_api"; /*URL*/
  var queryParams =
    "?" + encodeURIComponent("serviceKey") + "=" + EncodingKey; /*Service Key*/
  queryParams +=
    "&" + encodeURIComponent("ctprvnNm") + "=" + encodeURIComponent(ctprvnNm);

  queryParams +=
    "&" +
    encodeURIComponent("signguNm") +
    "=" +
    encodeURIComponent(signguNm); /**/
  queryParams +=
    "&" +
    encodeURIComponent("numOfRows") +
    "=" +
    encodeURIComponent("100"); /**/
  queryParams +=
    "&" + encodeURIComponent("type") + "=" + encodeURIComponent("json"); /**/
  queryParams +=
    "&" + encodeURIComponent("pageNo") + "=" + encodeURIComponent("1"); /**/
  //queryParams +=
  //"&" + encodeURIComponent("tfclghtSe") + "=" + encodeURIComponent("02"); /**/

  xhr.open("GET", url + queryParams);

  xhr.onreadystatechange = function () {
    if (this.readyState == 4) {
      responseText = JSON.parse(this.responseText);
      if (responseText.response.header.resultCode == 00) {
        items = responseText.response.body.items;
        items.forEach((item) => {
          pinpoint(
            item.sgnaspOrdr +
              " " +
              item.sgnaspTime +
              "\n" +
              item.opratnYn +
              " " +
              item.flashingLightOpenHhmm +
              " ~ " +
              item.flashingLightCloseHhmm,
            item.latitude,
            item.longitude,
            item.sgnaspOrdr,
            item.sgnaspTime,
            item.opratnYn
          );
          text += JSON.stringify(item) + "\n\n";
        });
      } else {
        text = ctprvnNm + " " + signguNm + " is null!\n";
      }
      app.innerText = text;
    }
  };

  xhr.send("");
}
