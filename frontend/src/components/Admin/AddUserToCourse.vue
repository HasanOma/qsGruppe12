<template>
  <div class="container-fluid d-flex justify-content-center align-items-center">
    <div class="card shadow mb-3" style="width: 50rem;">
      <div class="card-header py-3">
        <p class="text-primary m-0 fw-bold">Legg til student</p>
      </div>
      <div class="card-body">
        <form>
          <div class="row">
            <div class="col">
              <div class="mb-3">
                <label class="form-label">
                Velg emne
                <br>
              </label>
                <select class="form-select">
                <option value="12" selected="">
                  Emne 1
                </option>
                <option value="13">
                  Emne 2
                </option>
                <option value="14">
                  Emne 3
                </option>
              </select>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col">
              <div class="mb-3">
                <label class="form-label">
                  <strong>
                    Email Address
                  </strong>
                </label>
                <select class="form-select">
                <optgroup label="This is a group">
                  <option value="12" selected="">
                    This is item 1
                  </option>
                  <option value="13">
                    This is item 2
                  </option>
                  <option value="14">
                    This is item 3
                  </option>
                </optgroup>
              </select>
              </div>
            </div>
          </div>
        </form>
      </div>
      <div class="card-header py-3">
        <p class="text-primary m-0 fw-bold">
          Legg til flere studenter
        </p>
      </div>
      <div class="card-body">
        <form>
          <div class="row">
            <div class="col">
              <div class="mb-3">
                <label class="form-label">
                  Velg emne
                  <br>
                </label>
                <select class="form-select">
                <option value="12" selected="">
                  Emne 1
                </option>
                <option value="13">
                  Emne 2
                </option>
                <option value="14">
                  Emne 3
                </option>
              </select>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col">
              <div class="mb-3">
                <label class="form-label">
                  <strong>
                    Velg tag
                  </strong>
                  <br>
                </label>
                <BaseSelect/>
<!--                    options={{courses}}-->
                <select class="form-select">
                <optgroup label="This is a group">
                  <option value="12" selected="">
                    This is item 1
                  </option>
                  <option value="13">
                    This is item 2
                  </option>
                  <option value="14">
                    This is item 3
                  </option>
                </optgroup>
              </select>
              </div>
            </div>
          </div>
        </form>
        <BaseButton
            cssClass="btn btn-primary btn-sm"
            type="submit"
        >
          Legg til
        </BaseButton>
      </div>
    </div>
  </div>
</template>

<script>
import BaseButton from "@/components/BaseComponents/BaseButton";
import BaseSelect from "@/components/BaseComponents/BaseSelect";
export default {
  name: "AddUserToCourse",
  data() {
    return {
      courses: ["This is item 1", "This is item 2", "This is item 3"]
    }
  },
  components: {
    BaseButton,
    BaseSelect
  }
}
</script>

<style scoped>

</style>