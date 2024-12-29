<template>
  <div class="column">
    <BusFactor></BusFactor>
    <DevelopmentTrend></DevelopmentTrend>
  </div>


</template>

<script>
import BusFactor from "@/components/BusFactor";
import DevelopmentTrend from "@/components/DevelopmentTrend";
export default {
  name: "ColumnRight",
  components: {DevelopmentTrend, BusFactor}
}
</script>

<style scoped>
.column {
  width: 30%;
  display: flex;
  justify-content: space-between;
  flex-direction: column;
}
</style>
