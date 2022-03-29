<template>
    <div class="box">
        <div class="box__input">
            <svg class="box__icon" xmlns="http://www.w3.org/2000/svg" width="50" height="43" viewBox="0 0 50 43">
                <path d="M48.4 26.5c-.9 0-1.7.7-1.7 1.7v11.6h-43.3v-11.6c0-.9-.7-1.7-1.7-1.7s-1.7.7-1.7 1.7v13.2c0 .9.7 1.7 1.7 1.7h46.7c.9 0 1.7-.7 1.7-1.7v-13.2c0-1-.7-1.7-1.7-1.7zm-24.5 6.1c.3.3.8.5 1.2.5.4 0 .9-.2 1.2-.5l10-11.6c.7-.7.7-1.7 0-2.4s-1.7-.7-2.4 0l-7.1 8.3v-25.3c0-.9-.7-1.7-1.7-1.7s-1.7.7-1.7 1.7v25.3l-7.1-8.3c-.7-.7-1.7-.7-2.4 0s-.7 1.7 0 2.4l10 11.6z"></path>
            </svg>
            <input class="box__file" type="file" name="files[]" id="file" data-multiple-caption="{count} files selected" multiple />
            <label for="file"><strong>Velg fil</strong><span class="box__dragndrop"> eller dra og slipp den her</span>.</label>
            <button class="box__button" type="submit">Upload</button>
        </div>
        <div class="box__uploading">Uploading…</div>
        <div class="box__success">Done!</div>
        <div class="box__error">Error! <span></span>.</div>
    </div>
</template>

<script>
import $ from "jquery";

export default {
    name: "BaseDragnDrop",
    mounted() {
        const boxDragndrop = $('.box__dragndrop');
        const boxUploading = $('.box__uploading');
        const boxSuccess = $('.box__success');
        const boxError = $('.box__error');

        const form = $('.box');
        const div = document.createElement('div');
        let isAdvancedUpload = (('draggable' in div) || ('ondragstart' in div && 'ondrop' in div)) && 'FormData' in window && 'FileReader' in window;
        console.log(isAdvancedUpload);
        console.log(form);

        if(isAdvancedUpload) {
            form.addClass('has-advanced-upload');

            //var droppedFiles = false;

            form.on('drag dragstart dragend dragover dragenter dragleave drop', e => {
                e.preventDefault();
                e.stopPropagation();
            })
                .on('dragover dragenter', () => {
                    form.addClass('is-dragover');
                })
                .on('dragleave dragend drop', () => {
                    form.removeClass('is-dragover');
                })
                .on('drop', e => {
                    console.log(e)//droppedFiles = e.originalEvent.dataTransfer.files;
                });
        }
    },
}
</script>

<style scoped>
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