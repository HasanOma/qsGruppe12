/* eslint-disable */
<template>
  <div id="wrapper">
    <div class="d-flex flex-column" id="content-wrapper">
      <div id="content">
        <div class="container-fluid">
          <h3 class="text-dark mb-4">Admin settings</h3>
          <div class="row justify-content-center mb-3">
            <div class="col-lg-8">
              <div class="row mb-3 d-none">
                <div class="col">
                  <div class="card textwhite bg-primary text-white shadow">
                    <div class="card-body">
                      <div class="row mb-2">
                        <div class="col">
                          <p class="m-0">Peformance</p>
                          <p class="m-0"><strong>65.2%</strong></p>
                        </div>
                        <div class="col-auto">
                          <i class="fas fa-rocket fa-2x"> </i>
                        </div>
                      </div>
                      <p class="text-white-50 small m-0">
                        <i class="fas fa-arrow-up"> </i>&nbsp; 5% since last
                        month
                      </p>
                    </div>
                  </div>
                </div>
                <div class="col">
                  <div class="card textwhite bg-success text-white shadow">
                    <div class="card-body">
                      <div class="row mb-2">
                        <div class="col">
                          <p class="m-0">Peformance</p>
                          <p class="m-0"><strong>65.2%</strong></p>
                        </div>
                        <div class="col-auto">
                          <i class="fas fa-rocket fa-2x"></i>
                        </div>
                      </div>
                      <p class="text-white-50 small m-0">
                        <i class="fas fa-arrow-up"> </i>&nbsp; 5% since last
                        month
                      </p>
                    </div>
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="col" style="width: 903.328px">
                  <div class="card shadow mb-3">
                    <div class="card-header py-3">
                      <p class="text-primary m-0 fw-bold">Lag nytt emne</p>
                    </div>
                    <div class="card-body" style="height: auto">
                      <form>
                        <div class="row">
                          <div class="col">
                            <div class="mb-3">
                              <label class="form-label" for="emnekode">
                                <strong> Emnekode </strong>
                                <br />
                              </label>
                              <BaseInputNoLabel
                                class="form-control"
                                type="text"
                                id="emnekode"
                                placeholder="IDATTxxxx"
                                name="emnekode"
                              />
                            </div>
                          </div>
                          <div class="col">
                            <div class="mb-3">
                              <label class="form-label" for="emnenavn">
                                <strong> Emnenavn </strong>
                              </label>
                              <BaseInputNoLabel
                                class="form-control"
                                type="text"
                                id="emnenavn"
                                placeholder="Emnenavn"
                                name="emnenavn"
                              />
                            </div>
                          </div>
                        </div>
                        <div class="row">
                          <div class="col">
                            <div class="mb-3">
                              <label class="form-label" for="antall">
                                <strong> Antall øvinger </strong>
                              </label>
                              <BaseInputNoLabel
                                class="form-control"
                                type="number"
                                id="antall"
                                placeholder="0"
                                name="antall"
                              />
                            </div>
                          </div>
                          <div class="col">
                            <div class="mb-3">
                              <label class="form-label" for="godkjente">
                                <strong
                                  >Minste antall godkjente øvinger
                                </strong>
                              </label>
                              <BaseInputNoLabel
                                class="form-control"
                                type="number"
                                id="antall-3"
                                placeholder="0"
                                name="godkjente"
                              />
                            </div>
                          </div>
                        </div>
                        <div class="mb-3"></div>
                      </form>
                      <div></div>
                      <div
                        class="card shadow"
                        style="width: 100%; padding: 1rem"
                      >
                        <div class="mb-3" style="width: 100%; height: 5vh">
                          <label class="form-label" for>
                            &nbsp;Lag egne grupperingsregler:&nbsp;
                            <br />
                          </label>
                        </div>
                        <div class="mb-3">
                          <BaseInputNoLabel
                            type="number"
                            id="antall-1"
                            placeholder="0"
                            name="antall"
                          />
                          <label class="form-label" for="antall">
                            <strong>&nbsp;av øvingene&nbsp; </strong>
                          </label>
                          <BaseInputNoLabel
                            type="number"
                            id="antall-2"
                            placeholder="0"
                            name="antall"
                          />
                          <label class="form-label" for="antall">
                            <strong>&nbsp; til&nbsp; </strong>
                          </label>
                          <BaseInputNoLabel
                            type="number"
                            id="antall-3"
                            placeholder="0"
                            name="antall"
                          />
                          <label class="form-label" for="antall">
                            <strong>&nbsp; må være godkjent </strong>
                          </label>
                        </div>
                        <BaseButton
                          css-class="btn btn-primary btn-sm"
                          type="submit"
                          style="margin: 10px; max-width: 100px"
                          id="ruleBtn"
                        >
                          Lag regel
                        </BaseButton>
                        <label class="form-label" for="vis_regler">
                          Grupperingsreglene for emnet vil dukke opp her:&nbsp;
                          <br />
                        </label>
                        <div id="regelDiv" class="regelDiv"></div>
                      </div>
                      <BaseButton
                        css-class="btn btn-primary btn-sm"
                        type="submit"
                        style="margin: 10px"
                      >
                        Lagre
                      </BaseButton>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import BaseButton from "@/components/BaseButton";
import BaseInputNoLabel from "@/components/BaseInputNoLabel";
export default {
  name: "CreateSubject",
  components: {
    BaseButton,
    BaseInputNoLabel,
  },
};
</script>

<style scoped>
/*@import "src/assets/fonts/fontawesome-all.min.css";*/
</style>
