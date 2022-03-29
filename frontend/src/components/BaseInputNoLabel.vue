<template>
  <input
    v-bind="{
      ...$attrs,
      onInput: updateValue,
    }"
    :name="name"
    :type="input"
    :class="cssClass"
    :id="uuid"
    :value="modelValue"
    :placeholder="placeholder"
    :aria-describedby="error ? `${uuid}-error` : null"
    :aria-invalid="error ? true : false"
    :data-multiple-caption="multiples"
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
    multiples: {
      type: String,
    },
    name: {
      type: String,
    },
    input: {
      type: String
    },
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
