class ViewTaskActivity : AppCompatActivity() {
    private var textViewTaskName: TextView? = null
    private var textViewTaskDescription: TextView? = null
    private var textViewTaskPriority: TextView? = null
    private var textViewTaskDeadline: TextView? = null

    // Declare variables for task ID and ViewModel
    private var taskId = 0
    private var taskViewModel: TaskViewModel? = null
    @Override
    protected fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_task)

        // Initialize views
        textViewTaskName = findViewById(R.id.text_view_task_name)
        textViewTaskDescription = findViewById(R.id.text_view_task_description)
        textViewTaskPriority = findViewById(R.id.text_view_task_priority)
        textViewTaskDeadline = findViewById(R.id.text_view_task_deadline)

        // Initialize ViewModel
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        // Retrieve task ID from intent extras
        taskId = getIntent().getIntExtra("taskId", -1)

        // Observe task details LiveData and update UI
        taskViewModel.getTaskById(taskId).observe(this, object : Observer<Task?>() {
            @Override
            fun onChanged(task: Task?) {
                if (task != null) {
                    // Update UI with task details
                    textViewTaskName.setText(task.getName())
                    textViewTaskDescription.setText(task.getDescription())
                    textViewTaskPriority.setText(getString(R.string.priority, task.getPriority()))
                    textViewTaskDeadline.setText(getString(R.string.deadline, task.getDeadline()))
                }
            }
        })
    }
}
