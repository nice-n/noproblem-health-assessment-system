<template>
  <section>
    <ColumnLeft></ColumnLeft>
    <ColumnCenter></ColumnCenter>
    <ColumnRight></ColumnRight>
  </section>
</template>

<script>
import ColumnLeft from "@/views/ColumnLeft";
import ColumnCenter from "@/views/ColumnCenter";
import ColumnRight from "@/views/ColumnRight";
export default {
  name: "Main",
  components: {ColumnLeft,ColumnCenter,ColumnRight}
}
</script>

<style scoped>
section{
  width: 100%;
  height: calc(100% - 1.25rem);
  padding: 0.125rem;
  display: flex;
}
</style>
