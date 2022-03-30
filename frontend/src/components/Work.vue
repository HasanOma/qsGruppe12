<template>
  <div class="container d-flex justify-content-center justify-content-xxl-center">
    <div>
      <ul class="list-unstyled">
        <li class="list-item-style" v-for="task in work" :key="task">
          <div class="d-flex justify-content-between align-items-center list-item-div">
            <h1 class="list-header" style="">Øving {{ task.nr }}</h1>
            <i class="fa fa-check-circle list-icon"></i>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: "Ovinger",
  props: {
    work: []
  }
}
</script>

<style scoped>
.list-item-style {
  border-bottom: 1px none var(--bs-gray-600);
  background: var(--bs-gray-400);
  border-top-left-radius: 15px;
  border-top-right-radius: 15px;
  border-bottom-right-radius: 15px;
  border-bottom-left-radius: 15px;
  margin-bottom: 10px;
  box-shadow: 0px 3px 10px 2px var(--bs-gray-600);
}

.list-item-div {
  padding-top: 10px;
  padding-bottom: 10px;
  padding-right: 20px;
  padding-left: 20px;
  border-bottom-color: var(--bs-red);
}

.list-header {
  margin-right: 5rem;
}

.list-icon {
  font-size: 30px;
}
</style>