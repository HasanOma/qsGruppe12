<template>
  <div class="container-fluid d-flex flex-row justify-content-center">
    <div class="card shadow mb-3">
      <div class="card-header py-3">
        <p class="text-primary m-0 fw-bold">Lag nytt emne</p>
      </div>
      <div class="card-body h-auto">
        <form>
          <div class="row">
            <div class="col">
              <div class="mb-3">
                <label class="form-label" for="emnekode">
                  <strong>
                    Emnekode
                  </strong>
                  <br>
                </label>
                <input class="form-control" type="text" id="emnekode" placeholder="IDATTxxxx" name="emnekode">
              </div>
            </div>
            <div class="col">
              <div class="mb-3">
                <label class="form-label">
                  <strong>
                    Emnenavn
                  </strong>
                </label>
                <BaseInputNoLabel
                    cssClass="form-control"
                    type="text"
                    placeholder="Emnenavn"
                    name="emnenavn"
                    id="emnenavn"
                />
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col">
              <div class="mb-3">
                <label class="form-label">
                  <strong>
                    Antall øvinger
                  </strong>
                </label>
                <BaseInputNoLabel
                    cssClass="form-control"
                    type="number"
                    placeholder="0"
                    name="antall"
                    id="antall"
                />
              </div>
            </div>
            <div class="col">
              <div class="mb-3">
                <label class="form-label">
                  <strong>
                    Minste antall godkjente øvinger
                  </strong>
                </label>
                <BaseInputNoLabel
                    cssClass="form-control"
                    type="number"
                    placeholder="1"
                    name="godkjente"
                    id="godkjente"
                />
              </div>
            </div>
          </div>
        </form>
        <div></div>
        <div class="card shadow w-100 div-padding-rem">
          <div class="mb-3 w-100 div-height-vh">
            <label class="form-label">&nbsp;
              Lag egne grupperingsregler:&nbsp;
              <br>
            </label>
          </div>
          <div class="mb-3">
            <BaseInputNoLabel
                cssClass="form-control"
                type="number"
                placeholder="0"
                name="antall"
                id="antall-1"
            />
            <label class="form-label">
              <strong>&nbsp;
                av øvingene&nbsp;
              </strong>
            </label>
            <BaseInputNoLabel
                cssClass="form-control"
                type="number"
                placeholder="0"
                name="antall"
                id="antall-2"
            />
            <label class="form-label">
              <strong>&nbsp;
                til&nbsp;
              </strong>
            </label>
            <BaseInputNoLabel
                cssClass="form-control"
                type="text"
                placeholder="Emnenavn"
                name="emnenavn"
            />
            <label class="form-label">
              <strong>&nbsp;
                må være godkjent
              </strong>
            </label>
          </div>
          <BaseButton
              cssClass="btn btn-primary btn-sm btn-style"
              type="submit"
              id="ruleBtn"
          >
            Lag regel
          </BaseButton>
          <label class="form-label">
            Grupperingsreglene for emnet vil dukke opp her:&nbsp;
            <br>
          </label>
          <div id="regelDiv" class="regelDiv"></div>
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
import BaseInputNoLabel from "@/components/BaseComponents/BaseInputNoLabel";
import BaseButton from "@/components/BaseComponents/BaseButton";

export default {
  name: "CreateCourse",
  components: {
    BaseInputNoLabel,
    BaseButton
  }
}
</script>

<style scoped>
.btn-style {
  margin: 10px;
  max-width: 5rem;
}

.div-height-vh {
  height: 5vh;
}

.div-padding-rem {
  padding: 1rem;
}
</style>