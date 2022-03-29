<template>
    <nav class="navbar navbar-dark bg-secondary navbar-expand-md sticky-top navigation-clean-button nav-container">
        <div class="container-fluid">
            <button data-bs-toggle="collapse" class="navbar-toggler" data-bs-target="#navcol-1">
                <span class="visually-hidden">Toggle navigation</span>
                <span class="navbar-toggler-icon"></span>
            </button>
            <router-link to="/home" class="navbar-brand">
                <img src="../../assets/qS-logos.jpeg" class="nav-logo">
            </router-link>
            <div class="collapse navbar-collapse" id="navcol-1">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <router-link to="/home" class="nav-link active d-flex align-items-center" style="color: #000000;">
                            <i class="material-icons icon-size">school</i>&nbsp;Emner
                        </router-link>
                    </li>
                    <li class="nav-item" v-if="isTeacher">
                        <router-link to="/admin" class="nav-link active d-flex align-items-center" style="color: #000000;">
                            <i class="material-icons icon-size">home</i>&nbsp;Admin
                        </router-link>
                    </li>
                    <li class="nav-item">
                        <router-link to="/settings" class="nav-link d-flex align-items-center active" style="color: #000000;height: 41px;">
                            <i class="icon ion-android-settings icon-size"></i>&nbsp;Instillinger
                        </router-link>
                    </li>
                    <li class="nav-item">
                        <router-link to="/" class="nav-link d-flex align-items-center active" style="color: #000000;">
                            <i class="fa fa-sign-in icon-size"></i>&nbsp;Logg ut
                        </router-link>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</template>

<script>
export default {
    name: "NavbarStudent",
    data() {
        return {
            isTeacher: this.$store.getters.isTeacher,
        }
    }
}
</script>

<style scoped>
@import "../../assets/fonts/material-icons.min.css";

.nav-container {
    height: 80px;
    color: #ffffff;
    background: rgba(55,67,77,0);
}

.nav-logo {
    height: 999px;
    max-height: 41px;
}

.icon-size {
    font-size: 25px;
}
</style>