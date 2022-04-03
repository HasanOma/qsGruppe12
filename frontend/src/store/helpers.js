import { mapGetters } from "vuex";

export const authComp = {
  ...mapGetters(["loggedIn"]),
};
