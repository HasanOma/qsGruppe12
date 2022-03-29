<template>
  <label v-if="label" :for="uuid">
    {{ label }}
  </label>
  <input
    v-bind="{
      ...$attrs,
      onInput: updateValue,
    }"
    :id="uuid"
    :class="cssClass"
    :value="modelValue"
    :placeholder="label"
    :aria-describedby="error ? `${uuid}-error` : null"
    :aria-invalid="error ? true : false"
  />
  <BaseErrorMessage v-if="error" :id="`${uuid}-error`">
    {{ error }}
  </BaseErrorMessage>
</template>

<script>
import SetupForm from "@/feature/SetupForm";
import UniqueID from "@/feature/UniqueID";

export default {
  props: {
    label: {
      type: String,
      default: "",
    },
    error: {
      type: String,
      default: "",
    },
    modelValue: {
      type: [String, Number],
      default: "",
    },
    cssClass: {
      type: String,
      default: "form-control",
    },
  },
  setup(props, context) {
    const { updateValue } = SetupForm(props, context);
    const uuid = UniqueID().getID();

    return {
      updateValue,
      uuid,
    };
  },
};
</script>
