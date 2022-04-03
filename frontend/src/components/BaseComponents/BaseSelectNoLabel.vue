<template>
  <select
    :class="cssClass"
    v-bind="{
      ...$attrs,
      onChange: ($event) => {
        $emit('update:modelValue', $event.target.value);
      },
    }"
    :value="modelValue"
    :id="uuid"
    :aria-describedby="error ? `${uuid}-error` : null"
    :aria-invalid="error ? true : false"
  >
    <option
      v-for="option in options"
      :value="option"
      :key="option"
      :selected="option === modelValue"
    >
      {{ option }}
    </option>
  </select>
  <BaseErrorMessage v-if="error" :id="`${uuid}-error`">
    {{ error }}
  </BaseErrorMessage>
</template>

<script>
import BaseErrorMessage from "@/components/BaseComponents/BaseErrorMessage";
import SetupForm from "@/feature/SetupForm";
import UniqueID from "@/feature/UniqueID";

export default {
  components: {
    BaseErrorMessage,
  },
  props: {
    options: {
      type: Array,
      required: true,
    },
    error: {
      type: String,
      default: "",
    },
    modelValue: {
      type: [String, Number],
    },
    cssClass: {
      type: String,
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
