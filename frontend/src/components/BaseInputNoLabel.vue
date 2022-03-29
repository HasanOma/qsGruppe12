<template>
  <input
    v-bind="{
      ...$attrs,
      onInput: updateValue,
    }"
    :class="cssClass"
    :id="uuid"
    :value="modelValue"
    :placeholder="placeholder"
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
    cssClass: {
      type: String,
    },
    placeholder: {
      type: String,
    },
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
