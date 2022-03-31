<template>
  <label :class="labelClass" v-if="label" :for="uuid">
    {{ label }}
  </label>
  <select
    class="field"
    v-bind="{
      ...$attrs,
      onChange: ($event) => { $emit('update:modelValue', $event.target.value) },
    }"
    :value="modelValue"
    :id="uuid"
    :aria-describedby="error ? `${uuid}-error` : null"
    :aria-invalid="error ? true : false"
    :class="{ error }"
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
import SetupForm from "@/feature/SetupForm";
import UniqueID from "@/feature/UniqueID";

export default {
  props: {
    options: {
      type: Array,
      required: true,
    },
    label: {
      type: String,
      default: "",
    },
    labelClass: {
      type: String
    },
    error: {
      type: String,
      default: "",
    },
    modelValue: {
      type: [String, Number],
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
