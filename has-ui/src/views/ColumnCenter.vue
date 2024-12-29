<template>
  <div class="column">
    <AssessedValue></AssessedValue>
    <Leaderboard></Leaderboard>
  </div>
</template>

<script>

import AssessedValue from "@/components/AssessedValue";
import Leaderboard from "@/components/Leaderboard";
export default {
  name: "ColumnCenter",
  components: {Leaderboard, AssessedValue}
}
</script>

<style scoped>
.column{
  width: 40%;
  margin: 0 0.125rem;
  overflow: hidden;
  display: flex;
  justify-content: space-between;
  flex-direction: column;
}
</style>
