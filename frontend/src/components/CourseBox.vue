<template>
    <main class="page projets-page">
        <section class="portfolio-block project-no-images">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 col-lg-6">
                        <div class="project-card-no-image">
                            <h3 class="course-name">Nettverksprogrammering</h3>
                            <h4 class="course-id">IDATT2104</h4>
                            <div class="d-flex flex-row justify-content-between">
                                <button class="btn btn-outline-primary btn-sm rounded-pill">
                                    <i class="fa fa-check-circle icon-margin"></i>Øvinger
                                </button>
                                <button class="btn btn-outline-primary btn-sm rounded-pill">
                                    <i class="fa fa-arrow-circle-right icon-margin"></i>Til kø
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-6">
                        <div class="project-card-no-image">
                            <h3 class="course-name">Nettverksprogrammering</h3>
                            <h4 class="course-id">IDATT2104</h4>
                            <div class="d-flex flex-row justify-content-between">
                                <button class="btn btn-outline-primary btn-sm rounded-pill">
                                    <i class="fa fa-check-circle icon-margin"></i>Øvinger
                                </button>
                                <button class="btn btn-outline-primary btn-sm rounded-pill">
                                    <i class="fa fa-arrow-circle-right icon-margin"></i>Til kø
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-6">
                        <div class="project-card-no-image">
                            <h3 class="course-name">Nettverksprogrammering</h3>
                            <h4 class="course-id">IDATT2104</h4>
                            <div class="d-flex flex-row justify-content-between">
                                <button class="btn btn-outline-primary btn-sm rounded-pill">
                                    <i class="fa fa-check-circle icon-margin"></i>Øvinger
                                </button>
                                <button class="btn btn-outline-primary btn-sm rounded-pill">
                                    <i class="fa fa-arrow-circle-right icon-margin"></i>Til kø
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-6">
                        <div class="project-card-no-image">
                            <h3 class="course-name">Nettverksprogrammering</h3>
                            <h4 class="course-id">IDATT2104</h4>
                            <div class="d-flex flex-row justify-content-between"><a class="btn btn-outline-primary btn-sm" role="button" href="#"><i class="fa fa-check-circle" style="margin-right: 5px;"></i>Øvinger</a><a class="btn btn-outline-primary btn-sm" role="button" href="#"><i class="fa fa-arrow-circle-right" style="margin-right: 5px;"></i>Til kø</a></div>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-6">
                        <div class="project-card-no-image">
                            <h3 class="course-name">Nettverksprogrammering</h3>
                            <h4 class="course-id">IDATT2104</h4>
                            <div class="d-flex flex-row justify-content-between"><a class="btn btn-outline-primary btn-sm" role="button" href="#"><i class="fa fa-check-circle" style="margin-right: 5px;"></i>Øvinger</a><a class="btn btn-outline-primary btn-sm" role="button" href="#"><i class="fa fa-arrow-circle-right" style="margin-right: 5px;"></i>Til kø</a></div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </main>
</template>

<script>

export default {
    name: "CourseBox",
    mounted() {
        const plugin = document.createElement("script");
        plugin.setAttribute(
            "src",
            "https://cdnjs.cloudflare.com/ajax/libs/pikaday/1.6.1/pikaday.min.js"
        );
        plugin.async = true;
        document.head.appendChild(plugin);
    }
}
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css?family=Lato:300,400,700");
@import url("https://cdnjs.cloudflare.com/ajax/libs/pikaday/1.6.1/css/pikaday.min.css");
@import "../assets/fonts/font-awesome.min.css";

.project-card-no-image {
    box-shadow: 0px 2px 10px rgb(0 0 0 / 8%);
    padding: 35px;
    border-top: 4px solid #0ea0ff;
    margin-bottom: 30px;

}

.course-name {
    font-size: 1.3em;
    margin-bottom: 10px;
}

.course-id {
    font-size: 1em;
    opacity: .6;
    margin-bottom: 20px;
}

.icon-margin {
    margin-right: 5px;
}
</style>