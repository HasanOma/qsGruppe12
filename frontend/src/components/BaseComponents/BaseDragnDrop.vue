<template>
  <svg
    class="box__icon"
    xmlns="http://www.w3.org/2000/svg"
    width="50"
    height="43"
    viewBox="0 0 50 43"
  >
    <path
      d="M48.4 26.5c-.9 0-1.7.7-1.7
        1.7v11.6h-43.3v-11.6c0-.9-.7-1.7-1.7-1.7s-1.7.7-1.7
        1.7v13.2c0 .9.7 1.7 1.7 1.7h46.7c.9 0
        1.7-.7 1.7-1.7v-13.2c0-1-.7-1.7-1.7-1.7zm-24.5
        6.1c.3.3.8.5 1.2.5.4 0 .9-.2 1.2-.5l10-11.6c.7-.7.7-1.7
        0-2.4s-1.7-.7-2.4 0l-7.1 8.3v-25.3c0-.9-.7-1.7-1.7-1.7s-1.7.7-1.7
        1.7v25.3l-7.1-8.3c-.7-.7-1.7-.7-2.4 0s-.7 1.7 0 2.4l10 11.6z"
    ></path>
  </svg>
  <input
    class="box__file"
    type="file"
    name="files[]"
    id="file"
    data-multiple-caption="{count} files selected"
    multiple
  />
  <label for="file">
    <strong> Velg fil </strong>
    <span class="box__dragndrop"> eller dra og slipp den her </span>
    .
  </label>
</template>

<script>
import $ from "jquery";

export default {
  name: "BaseDragNDrop",

  mounted() {
    const boxDragndrop = $(".box__dragndrop");
    const boxUploading = $(".box__uploading");
    const boxSuccess = $(".box__success");
    const boxError = $(".box__error");

    boxDragndrop.css("display", "none");
    boxUploading.css("display", "none");
    boxSuccess.css("display", "none");
    boxError.css("display", "none");

    const form = $(".box");
    const div = document.createElement("div");
    let isAdvancedUpload =
      ("draggable" in div || ("ondragstart" in div && "ondrop" in div)) &&
      "FormData" in window &&
      "FileReader" in window;
    // console.log(isAdvancedUpload);
    // console.log(form);

    if (isAdvancedUpload) {
      form.addClass("has-advanced-upload");

      //var droppedFiles = false;

      form
        .on(
          "drag dragstart dragend dragover dragenter dragleave drop",
          function (e) {
            e.preventDefault();
            e.stopPropagation();
          }
        )
        .on("dragover dragenter", function () {
          form.addClass("is-dragover");
        })
        .on("dragleave dragend drop", function () {
          form.removeClass("is-dragover");
        })
        .on("drop", function (e) {
          console.log(e); //droppedFiles = e.originalEvent.dataTransfer.files;
        });
    }
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i");

.box.has-advanced-upload .box__dragndrop {
  display: inline;
}

.box__file {
  width: 0.1px;
  height: 0.1px;
  opacity: 0;
  overflow: hidden;
  position: absolute;
  z-index: -1;
}

.box.has-advanced-upload .box__icon {
  width: 100%;
  height: 80px;
  fill: #92b0b3;
  display: block;
  margin-bottom: 40px;
}
</style>
