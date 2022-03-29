<template>
  <div>
    <div class="d-flex flex-column" id="content-wrapper">
      <div id="content">
        <div class="container-fluid">
          <h3 class="text-dark mb-4">Admin settings</h3>
          <div id="wrapper">
            <div class="col-lg-8">
              <div class="row">
                <div class="col" style="width: 903.328px">
                  <div class="card shadow mb-3">
                    <div class="card-header py-3">
                      <p class="text-primary m-0 fw-bold">
                        Legg til studenter i gitt emne
                      </p>
                    </div>
                    <div class="card-body">
                      <form>
                        <div class="row">
                          <div class="col">
                            <div class="mb-3 label-align-left">
                              <label class="form-label"
                                >Choose Course<br
                              /></label>
                              <select class="form-select">
                                <option value="12" selected="">Emne 1</option>
                                <option value="13">Emne 2</option>
                                <option value="14">Emne 3</option>
                              </select>
                            </div>
                          </div>
                          <div class="col">
                            <div class="mb-3 label-align-left">
                              <label class="form-label">
                                <strong>Email Address</strong>
                              </label>
                              <BaseInputNoLabel
                                class="form-control"
                                type="email"
                                id="email"
                                placeholder="user@example.com"
                                name="email"
                              />
                            </div>
                          </div>
                        </div>
                        <div class="row">
                          <div class="col">
                            <div class="mb-3 label-align-left">
                              <label class="form-label">
                                <strong>First Name</strong>
                              </label>
                              <BaseInputNoLabel
                                cssClass="form-control"
                                type="text"
                                id="first_name"
                                placeholder="John"
                                name="first_name"
                              />
                            </div>
                          </div>
                          <div class="col">
                            <div class="mb-3 label-align-left">
                              <label class="form-label">
                                <strong>Last Name</strong>
                              </label>
                              <BaseInputNoLabel
                                cssClass="form-control"
                                type="text"
                                id="last_name"
                                placeholder="Doe"
                                name="last_name"
                              />
                            </div>
                          </div>
                        </div>
                        <h5>
                          Dra og slipp en CSV fil i boksen under eller trykk der
                          det står velg en fil:
                        </h5>
                        <div class="box">
                          <div class="box__input">
                            <BaseDragNDrop />
                            <BaseButton cssClass="box__button" type="submit">
                              Upload
                            </BaseButton>
                          </div>
                          <div class="box__uploading">Uploading…</div>
                          <div class="box__success">Done!</div>
                          <div class="box__error">Error! <span></span>.</div>
                        </div>
                      </form>
                      <BaseButton
                        css-class="btn btn-primary btn-sm"
                        type="submit"
                        style="margin: 10px"
                      >
                        Save Settings
                      </BaseButton>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="card shadow mb-5"></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import $ from "jquery";
import BaseButton from "@/components/BaseButton";
import BaseInputNoLabel from "@/components/BaseInputNoLabel";
import BaseDragNDrop from "@/components/BaseDragNDrop";
export default {
  name: "StudentCourse",
  components: {
    BaseButton,
    BaseInputNoLabel,
    BaseDragNDrop,
  },
  data() {
    return {
      advancedUploadPossible: false,
    };
  },
  mounted() {
    const plugin = document.createElement("script");
    plugin.setAttribute("src", "../assets/js/bootstrap.min.js");
    plugin.async = true;
    document.head.appendChild(plugin);

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
    console.log(isAdvancedUpload);
    console.log(form);

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

.label-align-left {
  text-align: left;
}

#wrapper {
  display: flex;
  justify-content: center;
}

.box {
  font-size: 1.25rem;
  background-color: #c8dadf;
  position: relative;
  padding: 100px 20px;
}

.box.has-advanced-upload {
  background-color: #c8dadf;
  outline: 2px dashed black;
  outline-offset: -10px;
}

.box.has-advanced-upload .box__dragndrop {
  display: inline;
}

.box.is-dragover {
  background-color: grey;
}

.box__file {
  width: 0.1px;
  height: 0.1px;
  opacity: 0;
  overflow: hidden;
  position: absolute;
  z-index: -1;
}

.box__button {
  font-weight: 700;
  color: #e5edf1;
  background-color: #39bfd3;
  display: none;
  padding: 8px 16px;
  margin: 40px auto 0;
}

.box__uploading {
  font-style: italic;
}

.box.has-advanced-upload .box__icon {
  width: 100%;
  height: 80px;
  fill: #92b0b3;
  display: block;
  margin-bottom: 40px;
}
</style>
