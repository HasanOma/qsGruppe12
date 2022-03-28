<template>
    <div id="wrapper">
        <div class="d-flex flex-column" id="content-wrapper">
            <div id="content">
                <div class="container-fluid">
                    <h3 class="text-dark mb-4">Admin settings</h3>
                    <div class="row mb-3">
                        <div class="col-lg-8">
                            <div class="row">
                                <div class="col" style="width: 903.328px;">
                                    <div class="card shadow mb-3">
                                        <div class="card-header py-3">
                                            <p class="text-primary m-0 fw-bold">Legg til studenter i gitt emne</p>
                                        </div>
                                        <div class="card-body">
                                            <form>
                                                <div class="row">
                                                    <div class="col">
                                                        <div class="mb-3 label-align-left">
                                                            <label class="form-label">Choose Course<br></label>
                                                            <select class="form-select">
                                                                <option value="12" selected="">Emne 1</option>
                                                                <option value="13">Emne 2</option>
                                                                <option value="14">Emne 3</option>
                                                            </select>
                                                        </div>
                                                    </div>
                                                    <div class="col">
                                                        <div class="mb-3 label-align-left">
                                                            <label class="form-label" for="email"><strong>Email Address</strong></label>
                                                            <input class="form-control" type="email" id="email" placeholder="user@example.com" name="email">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col">
                                                        <div class="mb-3 label-align-left">
                                                            <label class="form-label" for="first_name"><strong>First Name</strong></label>
                                                            <input class="form-control" type="text" id="first_name" placeholder="John" name="first_name">
                                                        </div>
                                                    </div>
                                                    <div class="col">
                                                        <div class="mb-3 label-align-left">
                                                            <label class="form-label" for="last_name"><strong>Last Name</strong></label>
                                                            <input class="form-control" type="text" id="last_name" placeholder="Doe" name="last_name">
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="box__input">
                                                    <input class="box__file" type="file" name="files[]" id="file" data-multiple-caption="{count} files selected" multiple />
                                                    <label for="file"><strong>Choose a file</strong><span class="box__dragndrop"> or drag it here</span>.</label>
                                                    <button class="box__button" type="submit">Upload</button>
                                                </div>
                                                <div class="box__uploading">Uploading…</div>
                                                <div class="box__success">Done!</div>
                                                <div class="box__error">Error! <span></span>.</div>
                                            </form>
                                            <button class="btn btn-primary btn-sm" type="submit" style="margin: 10px;">Save Settings</button>
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
export default {
    name: "StudentCourse",
    mounted() {
        const plugin = document.createElement("script");
        plugin.setAttribute(
            "src",
            "../assets/js/bootstrap.min.js"
        );
        plugin.async = true;
        document.head.appendChild(plugin);
    },
    methods: {
        isAdvancedUpload() {
            const div = document.createElement('div');
            return (('draggable' in div) || ('ondragstart' in div && 'ondrop' in div)) && 'FormData' in window && 'FileReader' in window;
        }
    }
}
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i");

.label-align-left {
    text-align: left;
}

.box__dragndrop,
.box__uploading,
.box__success,
.box__error {
    display: none;
}

.box.has-advanced-upload {
    background-color: white;
    outline: 2px dashed black;
    outline-offset: -10px;
}
.box.has-advanced-upload .box__dragndrop {
    display: inline;
}
</style>