<template>
  <div id="file-drag-drop-instant">
    <form id="drop-form-instant" @drop="handleFileDrop($event)">
      <span class="drop-files">Slipp fila her!</span>
    </form>

    <progress
      max="100"
      :value.prop="uploadPercentage"
      v-if="uploadPercentage !== 0"
    ></progress>

    <span v-show="uploadPercentage > 0"
      >Uploading {{ uploadPercentage }}%...</span
    >
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "DragNDrop",
  data() {
    return {
      dragAndDropCapable: false,
      files: [],
      uploadPercentage: 0,
    };
  },

  mounted() {
    /*
      Determine if drag and drop functionality is capable in the browser
    */
    this.dragAndDropCapable = this.determineDragAndDropCapable();

    /*
      If drag and drop capable, then we continue to bind events to our elements.
    */
    if (this.dragAndDropCapable) {
      this.bindEvents();
    }
  },

  methods: {
    bindEvents() {
      /*
        Listen to all of the drag events and bind an event listener to each
        for the fileform.
      */
      [
        "drag",
        "dragstart",
        "dragend",
        "dragover",
        "dragenter",
        "dragleave",
        "drop",
      ].forEach(
        function (evt) {
          /*
          For each event add an event listener that prevents the default action
          (opening the file in the browser) and stop the propagation of the event (so
          no other elements open the file in the browser)
        */
          document.getElementById("drop-form-instant").addEventListener(
            evt,
            function (e) {
              e.preventDefault();
              e.stopPropagation();
            }.bind(this),
            false
          );
        }.bind(this)
      );
    },

    handleFileDrop(event) {
      for (let i = 0; i < event.dataTransfer.files.length; i++) {
        this.files.push(event.dataTransfer.files[i]);
      }

      this.submitFiles();
    },

    determineDragAndDropCapable() {
      /*
        Create a test element to see if certain events
        are present that let us do drag and drop.
      */
      var div = document.createElement("div");

      /*
        Check to see if the `draggable` event is in the element
        or the `ondragstart` and `ondrop` events are in the element. If
        they are, then we have what we need for dragging and dropping files.

        We also check to see if the window has `FormData` and `FileReader` objects
        present so we can do our AJAX uploading
      */
      return (
        ("draggable" in div || ("ondragstart" in div && "ondrop" in div)) &&
        "FormData" in window &&
        "FileReader" in window
      );
    },

    submitFiles() {
      const form = new FormData();
      form.append("file", this.files[0]);

      const options = {
        method: "POST",
        url: "http://localhost:8080/users/add/file",
        headers: {
          "Content-Type": "multipart/form-data",
        },
        data: form,
      };

      axios
        .request(options)
        .then((response) => {
          console.log(response.data);
        })
        .catch((error) => {
          console.error(error);
        });
    },
  },
};
</script>

<style scoped>
form {
  display: block;
  height: 400px;
  width: 400px;
  background-color: #b9dfff;
  outline: 2px dashed black;
  outline-offset: -10px;
  margin: auto;
  margin-top: 40px;
  text-align: center;
  line-height: 400px;
  border-radius: 4px;
}

progress {
  width: 400px;
  margin: auto;
  display: block;
  margin-top: 20px;
  margin-bottom: 20px;
}
</style>
