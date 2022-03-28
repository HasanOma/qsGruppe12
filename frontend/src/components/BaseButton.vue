<template>
    <button
        :type="type"
        :class="cssClass"
    >
    <slot />
    </button>
</template>

<script>
export default {
    props: {
        cssClass: {
            type: String,
        },
        type: {
            type: String,
        }
    }
}
</script>