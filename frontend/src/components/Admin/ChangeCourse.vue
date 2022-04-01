<template>
  <div class="container-fluid d-flex flex-row justify-content-center">
    <div class="card shadow mb-3 width-50rem">
      <div class="card-header py-3">
        <p class="text-primary m-0 fw-bold">Rediger emne</p>
      </div>
      <div class="card-body h-auto">
        <form>
          <div class="row">
            <div class="col div-padding">
              <label class="form-label">Velg emne:
              </label>
              <select class="form-select">
              <optgroup label="This is a group">
                <option value="12" selected="">This is item 1
                </option>
                <option value="13">This is item 2
                </option>
                <option value="14">This is item 3
                </option>
              </optgroup>
            </select>
            </div>
          </div>
          <div class="row">
            <div class="col div-padding">
              <BaseButton
                  cssClass="btn btn-primary"
                  type="button"
              >
                Slett emne
              </BaseButton>
              </div>
          </div>
          <div class="row">
            <div class="col div-padding">
              <h5>Endre/slett regler
              </h5>
            </div>
          </div>
          <div class="row">
            <div class="col d-flex justify-content-center p-0">
              <div class="w-100 padding-20px">
                <ul class="list-unstyled">
                  <li>
                    <div class="d-flex flex-row justify-content-between">
                      <label class="form-label">
                        Regel nr 1
                      </label>
                      <div>
                        <i class="fa fa-pencil margin-right-10px">
                        </i>
                        <i class="fa fa-times">
                        </i>
                      </div>
                    </div>
                  </li>
                  <li>
                    <div class="d-flex flex-row justify-content-between">
                      <label class="form-label">
                        Regel nr 1
                      </label>
                      <div>
                        <i class="fa fa-pencil margin-right-10px">
                        </i>
                        <i class="fa fa-times">
                        </i>
                      </div>
                    </div>
                  </li>
                  <li>
                    <div class="d-flex flex-row justify-content-between">
                      <label class="form-label">
                      Regel nr 1
                    </label>
                      <div>
                        <i class="fa fa-pencil margin-right-10px">
                        </i>
                        <i class="fa fa-times">
                        </i>
                      </div>
                    </div>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </form>
        <div>
        </div>
        <BaseButton
            cssClass="btn btn-primary btn-sm btn-style"
            type="submit"
            id="ruleBtn"
        >
          Lagre
        </BaseButton>
      </div>
    </div>
  </div>
</template>

<script>
import BaseButton from "@/components/BaseComponents/BaseButton";

export default {
  name: "ChangeCourse",
  components: {
    BaseButton
  }
}
</script>

<style scoped>
.div-padding {
  padding: 0px;
  padding-top: 20px;
  padding-bottom: 20px;
  padding-left: 20px;
}

.margin-right-10px {
  margin-right: 10px;
}

.width-50rem {
  width: 50rem;
}

.padding-20px {
  padding: 20px;
}
</style>